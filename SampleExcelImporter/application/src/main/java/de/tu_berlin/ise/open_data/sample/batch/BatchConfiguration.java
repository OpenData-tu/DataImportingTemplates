package de.tu_berlin.ise.open_data.sample.batch;

import de.tu_berlin.ise.open_data.sample.model.Item;
import de.tu_berlin.ise.open_data.library.batch.JobCompletionNotificationListener;
import de.tu_berlin.ise.open_data.library.batch.JsonItemWriter;
import de.tu_berlin.ise.open_data.library.batch.StepProcessListener;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.AbstractExcelItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;

import java.io.*;

/**
 * Created by ahmadjawid on 5/21/17.
 * Configurations including jobs, job steps and how to read, write and process
 */

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;



    /**
     * Register a bean of {@link ExcelItemReader} which defines how to read data from the source
     * @return ExcelItemReader
     * */
    @Bean
    ItemReader reader() throws IOException, InvalidFormatException {
        AbstractExcelItemReader<Item> reader = new ExcelItemReader();

        //Set the resource to parse. 'source.xls' is at the root of the project
        reader.setResource(new InputStreamResource(new PushbackInputStream(new FileInputStream(new File("source.xls")))));

        //Skip the header
        reader.setLinesToSkip(1);

        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    /**
     * Excel document cannot be read using the Item class.
     * custom row mapper to map columns of excel file to attributes of the class
     * @return ExcelItemRowMapper
     */
    private RowMapper<Item> excelRowMapper() {
        return new ExcelItemRowMapper();
    }


    /**
     * Register a bean of {@link org.springframework.batch.item.ItemProcessor} which defines how to process individual objects
     * @return ExcelItemProcessor
     * */
    @Bean
    ItemProcessor<Item, String> processor() {
        return new ExcelItemProcessor();
    }


    /**
     * Register a bean of {@link org.springframework.batch.item.ItemWriter} which defines how to write individual json objects to kafka queue
     * @return JsonItemWriter
     * */
    @Bean
    ItemWriter<String> writer() {
        return new JsonItemWriter();
    }


    /**
     * Register a bean of {@link org.springframework.batch.core.StepExecutionListener} which defines
     * methods for listening to the events of processing steps and chunks
     * @return StepProcessListener
     * */
    @Bean
    public StepProcessListener stepExecutionListener() {
        return new StepProcessListener();
    }



    /**
     * Registers a job named 'sampleJob' that is finished in one step
     * @param listener
     * @return {@link Job}
     * */
    @Bean
    Job sampleJob(JobCompletionNotificationListener listener) throws IOException, InvalidFormatException {
        return jobBuilderFactory.get("sampleJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }



    /**
     * Registers a job step named 'step1' which defines how to read, process and write
     * @return {@link Job}
     * */
    @Bean
    Step step1() throws IOException, InvalidFormatException {

        return stepBuilderFactory.get("step1").listener(stepExecutionListener())
                .<Item, String>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
