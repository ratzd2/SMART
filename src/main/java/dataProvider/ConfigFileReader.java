package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;

public class ConfigFileReader {

	private Properties properties;
	private final String caminhoArquivoProperty= "configs//Configuration.properties";
 
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(caminhoArquivoProperty));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties nao encontrado em " + caminhoArquivoProperty);
		}		
	}
	
	public long getWait() {
		String wait = properties.getProperty("wait");
		if(wait != null) return Long.parseLong(wait);
		else throw new RuntimeException("wait nao especificado no arquivo Configuration.properties.");
	}
	
	public String getUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url nao especificado no arquivo Configuration.properties.");
	}

	public String getCaminhoDriverChrome(){
		String caminhoDriver = properties.getProperty("driverChrome");
		if(caminhoDriver!= null) return caminhoDriver;
		else throw new RuntimeException("Caminho do Driver nao especificado no arquivo Configuration.properties.");		
	}
	
	public String getCaminhoDriverFF(){
		String caminhoDriver = properties.getProperty("driverFF");
		if(caminhoDriver!= null) return caminhoDriver;
		else throw new RuntimeException("Caminho do Driver nao especificado no arquivo Configuration.properties.");		
	}
	
	public String getCaminhoDriverPhantom(){
		String caminhoDriver = properties.getProperty("driverPhantom");
		if(caminhoDriver!= null) return caminhoDriver;
		else throw new RuntimeException("Caminho do Driver nao especificado no arquivo Configuration.properties.");		
	}
	
	public String getCaminhoTestDataResource(){
		String testDataResourcePath = properties.getProperty("testDataResource");
		if(testDataResourcePath!= null) return testDataResourcePath;
		else throw new RuntimeException("Caminho do Test Data Resource nao especificado no arquivo Configuration.properties.");		
	}

	public String getCaminhoReport(){
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Caminho do Report Config nao especificado no arquivo Configuration.properties.");		
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		else if(browserName.equals("phantom")) return DriverType.PHANTOM;
		else throw new RuntimeException("Nome do Browser no arquivo Configuration.properties não foi localizador : " + browserName);
	}
	
}
