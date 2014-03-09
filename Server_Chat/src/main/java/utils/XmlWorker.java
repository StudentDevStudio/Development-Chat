package utils;

import users.User;
import users.UsersData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlWorker {
    private final Configurator config;
    private       UsersData    data;

    public XmlWorker(Configurator config) {
        this.config = config;
        data = new UsersData();
    }

    public void save() throws JAXBException {
        if (data == null) {
            // Logging
            return;
        }
        File saveFile = new File(config.getUserFilePath());
        JAXBContext context = JAXBContext.newInstance(UsersData.class);
        Marshaller marshall = context.createMarshaller();
        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshall.marshal(data, saveFile);
    }

    public UsersData load() throws JAXBException {
        File loadFile = new File(config.getUserFilePath());
        JAXBContext context = JAXBContext.newInstance(UsersData.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        data = (UsersData) unmarshaller.unmarshal(loadFile);
        return data == null ? new UsersData() : data;
    }

    public void setUsersData(UsersData data) {
        this.data = data;
    }

    public void setUserData(List<User> data) {
        this.data.setUsers(data);
    }
}
