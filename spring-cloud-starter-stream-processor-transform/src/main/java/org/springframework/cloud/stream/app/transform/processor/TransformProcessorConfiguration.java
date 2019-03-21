/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.app.transform.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.app.invoker.StreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;

/**
 * A Processor app that transforms messages using a SpEL expression.
 *
 * @author Eric Bottard
 * @author Marius Bogoevici
 * @author Gary Russell
 */
@EnableConfigurationProperties(TransformProcessorProperties.class)
@Configuration
public class TransformProcessorConfiguration {

	@Autowired
	private TransformProcessorProperties properties;

	@Bean
	@StreamHandler
	public Function<Message<?>,Object> transformFunc() {
		return t -> properties.getExpression().getValue(t);
	}

}
