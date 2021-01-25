/*
 * Copyright 2015-2018 the original author or authors.
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
package example.springdata.jpa.multipleds.customer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test for {@link CustomerRepository}.
 *
 * @author Oliver Gierke
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional(transactionManager = "customerTransactionManager")
public class CustomerRepositoryTests {

	@Autowired CustomerRepository repository;
	@Autowired @Qualifier("customerEntityManagerFactory") EntityManager em;

	@Test
	public void findsCustomerByLastname() {

		Optional<Customer> result = repository.findByLastname("Matthews");

		assertThat(result, is(not(Optional.empty())));
		assertThat(result.get().getFirstname(), is("Dave"));
	}
}
