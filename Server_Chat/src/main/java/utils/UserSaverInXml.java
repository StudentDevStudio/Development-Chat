package utils;

import users.UsersData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by IAUglov on 06.03.14.
 */
public class UserSaverInXml {
    public UsersData data;

    public UserSaverInXml(UsersData data) {
        this.data = data;
    }

    public void save() throws JAXBException {
        File saveFile = new File(Configurator.getInstance().getUserFilePath());
        JAXBContext context = JAXBContext.newInstance(UsersData.class);
        Marshaller marshall = context.createMarshaller();
        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshall.marshal(this.data, saveFile);
    }

    public UsersData load() throws JAXBException {
        File loadFile = new File(Configurator.getInstance().getUserFilePath());
        JAXBContext context = JAXBContext.newInstance(UsersData.class);
        Unmarshaller unmarshall = context.createUnmarshaller();
        return (UsersData) unmarshall.unmarshal(loadFile);
    }
}
