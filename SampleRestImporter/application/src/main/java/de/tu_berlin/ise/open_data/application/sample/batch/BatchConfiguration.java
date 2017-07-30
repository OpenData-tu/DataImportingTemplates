package de.tu_berlin.ise.open_data.application.sample.batch;


import de.tu_berlin.ise.open_data.application.sample.model.Item;
import de.tu_berlin.ise.open_data.library.batch.*;
import de.tu_berlin.ise.open_data.application.sample.config.ResourceProperties;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ahmadjawid on 7/2/17.
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
    private RestTemplate restTemplate;

    @Autowired
    private ResourceProperties resourceProperties;


    /**
     * Register a bean of {@link RestItemReader} which defines how to read data from the source
     * @return RestItemReader
     * */
    @Bean
    ItemReader<Item> itemReader() {
        return new RestItemReader(resourceProperties.getUrl(), restTemplate);
    }


    /**
     * Register a bean of {@link org.springframework.batch.item.ItemProcessor} which defines how to process individual objects
     * @return RestItemProcessor
     * */
    @Bean
    ItemProcessor<Item, String> itemProcessor() {
        return new RestItemProcessor();
    }

    /**
     * Register a bean of {@link org.springframework.batch.item.ItemWriter} which defines how to write individual json objects to kafka queue
     * @return JsonItemWriter
     * */
    @Bean
    ItemWriter<String> itemWriter() {
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
     * Registers a job named 'sampleRestJob' that is finished in one step
     * @param listener
     * @return {@link Job}
     * */
    @Bean
    Job sampleRestJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("sampleRestJob")
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
    Step step1() {
        return stepBuilderFactory.get("step1").listener(stepExecutionListener())
                .<Item, String>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

}