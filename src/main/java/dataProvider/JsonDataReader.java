package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import managers.FileReaderManager;
import testDataTypes.ExemploTestDataJson;

public class JsonDataReader {
	private static final String CAMINHO_TEST_DATA_RESOURCE = FileReaderManager.getInstance().getConfigReader()
			.getCaminhoTestDataResource();
	private final String arquivoJson = "Exemplo.json";

	private List<ExemploTestDataJson> exemploList;

	public JsonDataReader() {
		exemploList = getCustomerData();
	}

	private List<ExemploTestDataJson> getCustomerData() {
		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(CAMINHO_TEST_DATA_RESOURCE + "\\" + arquivoJson));
			ExemploTestDataJson[] customers = gson.fromJson(bufferReader, ExemploTestDataJson[].class);
			return Arrays.asList(customers);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Arquvivo Json nao encontrado no caminho : " + CAMINHO_TEST_DATA_RESOURCE + "\\"+ arquivoJson);
		} finally {
			try {
				if (bufferReader != null)
					bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	public final ExemploTestDataJson getPesquisa(String pesquisaName) {
		return exemploList.stream().filter(x -> x.pesquisa.equalsIgnoreCase(pesquisaName)).findAny().get();
	}

}
