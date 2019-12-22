package br.com.thales.springbatch.chunks;

import java.util.logging.Logger;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

import br.com.springbatch.model.Line;
import br.com.thales.springbatch.utils.FileUtils;

public class LineReader implements ItemReader<Line>, StepExecutionListener {

	private final Logger logger = Logger.getLogger(LineReader.class.getName());

	private FileUtils fu;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		fu = new FileUtils("input/input.csv");
		logger.info("Line Reader initialized.");
	}

	@Override
	public Line read() throws Exception {
		Line line = fu.readLine();
		if (line != null)
			logger.info("Read line: " + line.toString());
		return line;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		fu.closeReader();
		logger.info("Line Reader ended.");
		return ExitStatus.COMPLETED;
	}
	
}
