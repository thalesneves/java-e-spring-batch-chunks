package br.com.thales.springbatch.chunks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import br.com.springbatch.model.Line;

public class LineProcessor implements ItemProcessor<Line, Line>, StepExecutionListener {

	private Logger logger = Logger.getLogger(LineProcessor.class.getName());

	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("Line Processor initialized.");
	}

	@Override
	public Line process(Line line) throws Exception {
		long age = ChronoUnit.YEARS.between(line.getLocalDate(), LocalDate.now());
		logger.info("Calculated age " + age + " for line " + line.toString());
		line.setAge(age);
		return line;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("Line Processor ended.");
		return ExitStatus.COMPLETED;
	}
}
