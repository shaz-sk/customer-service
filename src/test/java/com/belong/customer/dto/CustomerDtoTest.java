package com.belong.customer.dto;


import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.rule.Rule;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.utils.ValidationHelper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CustomerDtoTest {
    List<Rule> rules;
    List<Tester> testers;

    @BeforeEach
    public void setUp(){

        rules = Lists.newArrayList();
        rules.add(new GetterMustExistRule());
        rules.add(new SetterMustExistRule());
        rules.add(new EqualsAndHashCodeMatchRule());

        testers = Lists.newArrayList();
        testers.add(new GetterTester());
        testers.add(new SetterTester());
    }

    @Test
    public void test(){
        PojoClass pojoClass = PojoClassFactory.getPojoClass(CustomerDto.class);
        ValidationHelper.runValidation(pojoClass, rules, testers);
    }

}