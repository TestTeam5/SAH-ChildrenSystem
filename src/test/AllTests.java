package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DeletedNewsGetterTest.class, 
		InitializerTest.class, 
		NewsDetailReaderTest.class, 
		NewsGetterTest.class,
		NewsListTest.class, 
		StatisticsGetterTest.class, 
		XMLReaderTest.class, 
		XMLWriterTest.class })

public class AllTests {
	
}
