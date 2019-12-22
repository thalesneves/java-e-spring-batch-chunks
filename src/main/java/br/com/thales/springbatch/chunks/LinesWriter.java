package br.com.thales.springbatch.chunks;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.core.StepExecution;

import br.com.springbatch.model.Line;
import br.com.thales.springbatch.utils.FileUtils;

public class LinesWriter implements ItemWriter<Line>, StepExecutionListener {

	private final Logger logger = Logger.getLogger(LinesWriter.class.getName());

	private FileUtils fu;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		fu = new FileUtils("csv/output.csv");
		logger.info("Line Writer initialized.");
	}
	
	@Override
	public void write(List<? extends Line> lines) throws Exception {
		for (Line line : lines) {
			fu.writeLine(line);
			logger.info("Wrote line " + line.toString());
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		fu.closeWriter();
		logger.info("Line Writer ended.");
		return ExitStatus.COMPLETED;
	}

}
