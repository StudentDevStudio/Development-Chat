package utils;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import users.User;
import users.UsersData;

public class XmlWorker {
	private UsersData data = new UsersData();

	public void save() throws JAXBException {
		if (data == null) {
			// Logging
			return;
		}
		File saveFile = new File(Configurator.getInstance().getUserFilePath());
		JAXBContext context = JAXBContext.newInstance(UsersData.class);
		Marshaller marshall = context.createMarshaller();
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshall.marshal(data, saveFile);
	}

	public UsersData load() throws JAXBException {
		File loadFile = new File(Configurator.getInstance().getUserFilePath());
		JAXBContext context = JAXBContext.newInstance(UsersData.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (UsersData) unmarshaller.unmarshal(loadFile);
	}

	public void setUsersData(UsersData data) {
		this.data = data;
	}

	public void setUserData(List<User> data) {
		this.data.setUsers(data);
	}
}
