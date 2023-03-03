package seleniumFrameworkDesign.data;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getjsonDataToMap(String filePath) throws IOException {
		String json_content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		// for converting String to HashMap --> add jackson databind dependency to
		// pom.xml file
		ObjectMapper mapper = new ObjectMapper();

		TypeReference<List<HashMap<String, String>>> ref = new TypeReference<List<HashMap<String, String>>>() {
		};
		List<HashMap<String, String>> data = mapper.readValue(json_content, ref);

		return data;
	}

}
