package de.tu_berlin.ise.open_data.application.flat.batch;

import de.tu_berlin.ise.open_data.application.flat.model.Item;
import de.tu_berlin.ise.open_data.library.batch.StepProcessListener;
import de.tu_berlin.ise.open_data.library.batch.JobCompletionNotificationListener;
import de.tu_berlin.ise.open_data.library.batch.JsonItemWriter;
import de.tu_berlin.ise.open_data.library.service.ApplicationService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;


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

    @Autowired
    public ApplicationService applicationService;



    /**
     * Register a bean of {@link org.springframework.batch.item.ItemReader} which defines how to read data from the source
     * @return FlatFileItemReader
     * */
    @Bean
    FlatFileItemReader itemReader() throws InstantiationException, IllegalAccessException {
        CustomItemReader flatFileItemReader = new CustomItemReader();
        //'data.csv' is inside 'resources' directory
        flatFileItemReader.setResource(new ClassPathResource("data.csv"));
        // Set n first lines to skip parsing
        flatFileItemReader.setLinesToSkip(1);

        //Set LineMapper which defines how source rows are parsed into Java Objects
        flatFileItemReader.setLineMapper(applicationService.createLineMapper(Item.class));

        return flatFileItemReader;
    }



    /**
     * Register a bean of {@link org.springframework.batch.item.ItemProcessor} which defines how to process individual objects
     * @return FlatItemProcessor
     * */
    @Bean
    public FlatItemProcessor itemProcessor() {
        return new FlatItemProcessor();
    }



    /**
     * Register a bean of {@link org.springframework.batch.item.ItemWriter} which defines how to write individual json objects to kafka queue
     * @return JsonItemWriter
     * */
    @Bean
    public JsonItemWriter itemWriter() {
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
    public Job sampleJob(JobCompletionNotificationListener listener) throws NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, MalformedURLException {
        return jobBuilderFactory.get("sampleJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }


    /**
     * Registers a job step named 'step1' which defines how to read, process and write
     * @return {@link Step}
     * */
    @Bean
    public Step step1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, MalformedURLException, ClassNotFoundException {
        return stepBuilderFactory.get("step1").listener(stepExecutionListener())
                .<Item, String>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }
}
