package start;

import entity.LegalDocumentType;
import entity.PersonalProperty;
import entity.Request;
import entity.User;
import entity.enums.RequestStatus;
import entity.enums.Roles;
import presentation.view.LogInWindow;
import repository.LegalDocumentTypeRepo;
import repository.PersonalPropertyRepo;
import repository.RequestRepo;
import repository.UserRepo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ApplicationStart {

    public static void main(String[] args) {
        //ApplicationStart app=new ApplicationStart();
       // app.initApp();
        LogInWindow window=new LogInWindow();
    }

    /**
     * <b>IMPORTANT: RUN ONLY ONCE AND COMMENT AFTER INSERTIONS ARE DONE</b>. Run it again only if you drop the database
     */
    public void initApp(){
        User user1=new User();
        user1.setEMail("iam.suffering@gmail.com");
        user1.setCnp(("6000609420691"));
        user1.setFirstName("Iam");
        user1.setLastName("Suffering");
        user1.setPassword(("suffer").toCharArray());
        user1.setRole(Roles.ADMIN);
        user1.autofillFields();

        User user2=new User();
        user2.setEMail("eternalpain@yahoo.com");
        user2.setCnp("1951042018902");
        user2.setFirstName("Eternal");
        user2.setLastName("Pain");
        user2.setPassword(("pain").toCharArray());
        user2.setRole(Roles.ADMIN);
        user2.autofillFields();

        User user3=new User();
        user3.setEMail("endless-torment@gmail.com");
        user3.setCnp(("5001224463631"));
        user3.setFirstName("Endless");
        user3.setLastName("Torment");
        user3.setPassword(("torment").toCharArray());
        user3.setRole(Roles.ADMIN);
        user3.autofillFields();

        User user4=new User();
        user4.setEMail("elena09@yahoo.com");
        user4.setCnp("2011009463661");
        user4.setFirstName("Elena");
        user4.setLastName("Gheorghe");
        user4.setPassword(("elena").toCharArray());
        user4.setRole(Roles.USER);
        user4.autofillFields();

        User user5=new User();
        user5.setEMail("gheorghe.gheorghe@yahoo.com");
        user5.setCnp("5801123156472");
        user5.setFirstName("Gheorghe");
        user5.setLastName("Gheorghiu");
        user5.setPassword(("gheorghe").toCharArray());
        user5.setRole(Roles.USER);
        user5.autofillFields();

        User user6=new User();
        user6.setEMail("iliescuilie@gmail.com");
        user6.setCnp("5001224466631");
        user6.setFirstName("Ilie");
        user6.setLastName("Iliescu");
        user6.setPassword(("ilie").toCharArray());
        user6.setRole(Roles.USER);
        user6.autofillFields();

        try{
            user1.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-01-14 16:20"));
            user2.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-02-17 18:00"));
            user3.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-02-02 10:00"));
            user4.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2021-12-31 20:00"));
            user5.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-02-02 10:00"));
            user6.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-03-01 10:00"));
        }catch (ParseException e){
            e.printStackTrace();
        }

        LegalDocumentType type1=new LegalDocumentType();
        type1.setTypeName("Declararea unei cladiri");
        type1.setDescription("Un document cu scopul informarii primariei in legatura cu detinerea unei cladiri");
        type1.setForBuildings(true);

        LegalDocumentType type2=new LegalDocumentType();
        type2.setTypeName("Scoaterea din evidentele fiscale ale unui imobil");
        type2.setDescription("Un document cu scopul informarii primariei in legatura cu detinerea unei cladiri");
        type2.setForBuildings(true);

        LegalDocumentType type3=new LegalDocumentType();
        type3.setTypeName("Certificat deces");
        type3.setDescription("Un certificat pentru declararea decesului unei persoane");

        Request r1=new Request();
        r1.setRequester(user5);
        r1.setDocumentType(type3);
        r1.setUserMessage("I regret to announce that I am no longer alive");
        r1.autofillFields();

        Request r2=new Request();
        r2.setRequester(user5);
        r2.setDocumentType(type1);
        r2.autofillFields();

        Request r3=new Request();
        r3.setRequester(user6);
        r3.setDocumentType(type1);
        r3.autofillFields();

        try{
            r1.setRequestedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-01-14 16:20"));
            r2.setRequestedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2021-12-31 20:00"));
            r3.setRequestedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2022-02-02 10:00"));
        }catch (ParseException e){
            e.printStackTrace();
        }

        PersonalProperty property1=new PersonalProperty();
        property1.setDistrict("Cluj-Napoca");
        property1.setStreet("Observatorului");
        property1.setNumber("35");
        property1.setUser(user5);
        property1.autofillFields();

        PersonalProperty property2=new PersonalProperty();
        property2.setDistrict("Cluj-Napoca");
        property2.setStreet("Ceahlaului");
        property2.setNumber("40B");
        property2.setUser(user1);
        property2.autofillFields();

        UserRepo userRepo=new UserRepo();
        userRepo.insertNewUser(user1);
        userRepo.insertNewUser(user2);
        userRepo.insertNewUser(user3);
        userRepo.insertNewUser(user4);
        userRepo.insertNewUser(user5);
        userRepo.insertNewUser(user6);

        LegalDocumentTypeRepo legalDocumentTypeRepo=new LegalDocumentTypeRepo();

        legalDocumentTypeRepo.addDocumentType(type1);
        legalDocumentTypeRepo.addDocumentType(type2);
        legalDocumentTypeRepo.addDocumentType(type3);

        RequestRepo requestRepo=new RequestRepo();
        requestRepo.addRequest(r1);
        requestRepo.addRequest(r2);
        requestRepo.addRequest(r3);

        PersonalPropertyRepo personalPropertyRepo=new PersonalPropertyRepo();
        personalPropertyRepo.insertNewAddress(property1);
        personalPropertyRepo.insertNewAddress(property2);

    }

}
