package com.nacu.medicaloffices.bootstrap;

import com.nacu.medicaloffices.domain.*;
import com.nacu.medicaloffices.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PharmacyOwnerRepository pharmacyOwnerRepository;
    private final SpecialtyRepository specialtyRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineStockRepository medicineStockRepository;
    private final MedicalOfficeRepository medicalOfficeRepository;
    private final AppointmentRepository appointmentRepository;
    private final RecipeRepository recipeRepository;
    private final PharmacyRepository pharmacyRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataLoader(DoctorRepository doctorRepository, PatientRepository patientRepository, PharmacyOwnerRepository pharmacyOwnerRepository, SpecialtyRepository specialtyRepository, MedicineRepository medicineRepository, MedicineStockRepository medicineStockRepository, MedicalOfficeRepository medicalOfficeRepository, AppointmentRepository appointmentRepository, RecipeRepository recipeRepository, PharmacyRepository pharmacyRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.pharmacyOwnerRepository = pharmacyOwnerRepository;
        this.specialtyRepository = specialtyRepository;
        this.medicineRepository = medicineRepository;
        this.medicineStockRepository = medicineStockRepository;
        this.medicalOfficeRepository = medicalOfficeRepository;
        this.appointmentRepository = appointmentRepository;
        this.recipeRepository = recipeRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = userRepository.count();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        loadMedicines();
        loadSpecialties();

        loadRoles();
        loadUsers();

        loadMedicalOffices();
        loadPharmacies();

        loadAppointments();
        loadRecipes();
        loadMedicineStock();
    }

    private void loadRoles() {
        roleRepository.save(
                Role.builder()
                        .name("admin")
                        .build()
        );
        roleRepository.save(
                Role.builder()
                        .name("doctor")
                        .build()
        );
        roleRepository.save(
                Role.builder()
                        .name("pharmacy_owner")
                        .build()
        );
        roleRepository.save(
                Role.builder()
                        .name("patient")
                        .build()
        );
    }

    private void loadUsers() {
        userRepository.save(
                User.builder()
                        .username("admin77")
                        .password(new BCryptPasswordEncoder().encode("admin77"))
                        .role(roleRepository.findByName("admin"))
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("florin")
                        .password(new BCryptPasswordEncoder().encode("florin"))
                        .patient(patientRepository.findByLastName("Nacu"))
                        .role(roleRepository.findByName("patient"))
                        .patient(
                                Patient.builder()
                                        .firstName("Florin")
                                        .lastName("Nacu")
                                        .contactData(
                                                ContactData.builder()
                                                        .email("florin@gmail.com")
                                                        .phoneNumber("0769876511")
                                                        .build())
                                        .build())
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("andreea")
                        .password(new BCryptPasswordEncoder().encode("andreea"))
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .role(roleRepository.findByName("doctor"))
                        .doctor(
                                Doctor.builder()
                                        .firstName("Andreea")
                                        .lastName("Cristea")
                                        .contactData(
                                                ContactData.builder()
                                                        .email("andreea@gmail.com")
                                                        .phoneNumber("0761234560")
                                                        .build())
                                        .build())
                        .build()
        );
        userRepository.save(
                User.builder()
                        .username("gica")
                        .password(new BCryptPasswordEncoder().encode("gica"))
                        .pharmacyOwner(pharmacyOwnerRepository.findByLastName("Petrescu"))
                        .role(roleRepository.findByName("pharmacy_owner"))
                        .pharmacyOwner(
                                PharmacyOwner.builder()
                                        .firstName("Gica")
                                        .lastName("Petrescu")
                                        .contactData(
                                                ContactData.builder()
                                                        .email("gica@gmail.com")
                                                        .phoneNumber("0731234567")
                                                        .build())
                                        .build())
                        .build()
        );
    }

    private void loadMedicines() {
        medicineRepository.save(
                Medicine.builder()
                        .name("Paracetamol")
                        .imageUrl("https://static.apoteca-farmacie.ro/assets/img/p/1446816-12085-large.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Nurofen")
                        .imageUrl("https://media-services.digital-rb.com/s3/live-productcatalogue/sys-master/images/heb/hd6/8869324357662/RBL1902106_Nurofen%20200%20mg%20tabs_carton%2012_fata07C%20Pack_Simulation.png")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Aspirin")
                        .imageUrl("https://www.pilulka.ro/assets/image/2020-03/1584638093-aspirin-cardio-100-mg-28-comprimate-gastrorezistente.jpg?v=1584638093")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Amoxacilina")
                        .imageUrl("https://www.drogueriascafam.com.co/48740-large_default/comprar-en-cafam-amoxicilina-500-mg-caja-con-50-capsulas-precio.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Furazolidon")
                        .imageUrl("https://cdn.contentspeed.ro/alphega.websales.ro/cs-content/cs-photos/products/original/furazolidon-terapia-100-mg-x-20-compr-100mg_17148_1_1576137514.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Nospa")
                        .imageUrl("https://www.argefarm.ro/19508/no-spa-40mg.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Fasconal")
                        .imageUrl("https://comenzi.farmaciatei.ro/gallery/13429/fasconal-20-comprimate-gedeon-richter-romania-3574.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Ibuprofen")
                        .imageUrl("https://comenzi.farmaciatei.ro/images/products-photos/comenzi.farmaciatei.ro/ibuprofen-200-20-comprimate-imedica-10016954.gif")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Bilichol")
                        .imageUrl("https://www.farmacialapretmic.ro/8577-thickbox_default/bilichol-x-24-capsule.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Antinevralgic")
                        .imageUrl("https://comenzi.farmaciatei.ro/gallery/16379/antinevralgic-forte-20-comprimate-zentiva-3716.jpg")
                        .build()
        );
        medicineRepository.save(
                Medicine.builder()
                        .name("Panadol")
                        .imageUrl("https://cdn.contentspeed.ro/0723028969.websales.ro/cs-content/cs-photos/products/original/panadol-rapid-500mg-12-comprimate-gsk_66346_1_1594637915.jpg")
                        .build()
        );
    }

    private void loadSpecialties() {
        specialtyRepository.save(
                Specialty.builder()
                        .name("Dentistry")
                        .build()
        );
        specialtyRepository.save(
                Specialty.builder()
                        .name("Neurology")
                        .build()
        );
        specialtyRepository.save(
                Specialty.builder()
                        .name("Gastroenterology")
                        .build()
        );
        specialtyRepository.save(
                Specialty.builder()
                        .name("Hematology")
                        .build()
        );
        specialtyRepository.save(
                Specialty.builder()
                        .name("Gynecology")
                        .build()
        );
        specialtyRepository.save(
                Specialty.builder()
                        .name("Pediatrics")
                        .build()
        );
        specialtyRepository.save(
                Specialty.builder()
                        .name("Nefrology")
                        .build()
        );
    }

    private void loadMedicalOffices() {
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Dentist Office")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Iasi")
                                        .street("Calea Aricilor")
                                        .number(35)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office@gmail.com")
                                        .phoneNumber("0761234561")
                                        .build())
                        .specialty(specialtyRepository.findByName("Dentistry"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(9, 0))
                                        .mondayEnd(LocalTime.of(17, 0))
                                        .tuesdayStart(LocalTime.of(9, 0))
                                        .tuesdayEnd(LocalTime.of(17, 0))
                                        .wednesdayStart(LocalTime.of(9, 0))
                                        .wednesdayEnd(LocalTime.of(17, 0))
                                        .thursdayStart(LocalTime.of(9, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(9, 0))
                                        .fridayEnd(LocalTime.of(18, 0))
                                        .build())
                        .build()
        );
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Neurology Office")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Iasi")
                                        .street("Calea Pisicilor")
                                        .number(12)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office2@gmail.com")
                                        .phoneNumber("0761234562")
                                        .build())
                        .specialty(specialtyRepository.findByName("Neurology"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(7, 0))
                                        .mondayEnd(LocalTime.of(19, 0))
                                        .tuesdayStart(LocalTime.of(8, 0))
                                        .tuesdayEnd(LocalTime.of(19, 0))
                                        .wednesdayStart(LocalTime.of(7, 0))
                                        .wednesdayEnd(LocalTime.of(19, 0))
                                        .thursdayStart(LocalTime.of(8, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(8, 0))
                                        .fridayEnd(LocalTime.of(17, 0))
                                        .build())
                        .build()
        );
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Gynecology Office")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Cluj")
                                        .street("Stefan cel Mare")
                                        .number(122)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office3@gmail.com")
                                        .phoneNumber("0761234521")
                                        .build())
                        .specialty(specialtyRepository.findByName("Gynecology"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(8, 0))
                                        .mondayEnd(LocalTime.of(19, 0))
                                        .tuesdayStart(LocalTime.of(8, 0))
                                        .tuesdayEnd(LocalTime.of(19, 0))
                                        .wednesdayStart(LocalTime.of(7, 0))
                                        .wednesdayEnd(LocalTime.of(19, 0))
                                        .thursdayStart(LocalTime.of(8, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(8, 0))
                                        .fridayEnd(LocalTime.of(19, 0))
                                        .build())
                        .build()
        );
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Dentist Office 2")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Tecuci")
                                        .street("Stefan cel Mare")
                                        .number(1321)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office3@gmail.com")
                                        .phoneNumber("0761234521")
                                        .build())
                        .specialty(specialtyRepository.findByName("Dentistry"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(8, 0))
                                        .mondayEnd(LocalTime.of(14, 0))
                                        .tuesdayStart(LocalTime.of(8, 0))
                                        .tuesdayEnd(LocalTime.of(19, 0))
                                        .wednesdayStart(LocalTime.of(7, 0))
                                        .wednesdayEnd(LocalTime.of(19, 0))
                                        .thursdayStart(LocalTime.of(8, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(8, 0))
                                        .fridayEnd(LocalTime.of(19, 0))
                                        .build())
                        .build()
        );
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Gynecology Office 2")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Bucuresti")
                                        .street("Calea Grivitei")
                                        .number(51)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office4@gmail.com")
                                        .phoneNumber("0763234521")
                                        .build())
                        .specialty(specialtyRepository.findByName("Gynecology"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(8, 0))
                                        .mondayEnd(LocalTime.of(19, 0))
                                        .tuesdayStart(LocalTime.of(8, 0))
                                        .tuesdayEnd(LocalTime.of(15, 0))
                                        .wednesdayStart(LocalTime.of(7, 0))
                                        .wednesdayEnd(LocalTime.of(19, 0))
                                        .thursdayStart(LocalTime.of(8, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(8, 0))
                                        .fridayEnd(LocalTime.of(11, 0))
                                        .build())
                        .build()
        );
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Gastroenterology Office")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Cluj")
                                        .street("Stefan cel Mare")
                                        .number(122)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office5@gmail.com")
                                        .phoneNumber("0761212521")
                                        .build())
                        .specialty(specialtyRepository.findByName("Gastroenterology"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(8, 0))
                                        .mondayEnd(LocalTime.of(19, 0))
                                        .tuesdayStart(LocalTime.of(8, 0))
                                        .tuesdayEnd(LocalTime.of(17, 0))
                                        .wednesdayStart(LocalTime.of(7, 0))
                                        .wednesdayEnd(LocalTime.of(19, 0))
                                        .thursdayStart(LocalTime.of(8, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(8, 0))
                                        .fridayEnd(LocalTime.of(19, 0))
                                        .build())
                        .build()
        );
        medicalOfficeRepository.save(
                MedicalOffice.builder()
                        .name("Cristea's Pediatrics Office")
                        .doctor(doctorRepository.findByLastName("Cristea"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Galati")
                                        .street("Vlad Tepes")
                                        .number(98)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("andreea_office6@gmail.com")
                                        .phoneNumber("0761234521")
                                        .build())
                        .specialty(specialtyRepository.findByName("Gynecology"))
                        .schedule(
                                Schedule.builder()
                                        .mondayStart(LocalTime.of(8, 0))
                                        .mondayEnd(LocalTime.of(19, 0))
                                        .tuesdayStart(LocalTime.of(8, 0))
                                        .tuesdayEnd(LocalTime.of(19, 0))
                                        .wednesdayStart(LocalTime.of(12, 0))
                                        .wednesdayEnd(LocalTime.of(19, 0))
                                        .thursdayStart(LocalTime.of(8, 0))
                                        .thursdayEnd(LocalTime.of(17, 0))
                                        .fridayStart(LocalTime.of(8, 0))
                                        .fridayEnd(LocalTime.of(19, 0))
                                        .build())
                        .build()
        );
    }

    private void loadPharmacies() {
        pharmacyRepository.save(
                Pharmacy.builder()
                        .name("Catena")
                        .pharmacyOwner(pharmacyOwnerRepository.findByLastName("Petrescu"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Bucuresti")
                                        .street("Calea Grivitei")
                                        .number(143)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("catena@gmail.com")
                                        .phoneNumber("0761234532")
                                        .build())
                        .build()
        );
        pharmacyRepository.save(
                Pharmacy.builder()
                        .name("Apoteca")
                        .pharmacyOwner(pharmacyOwnerRepository.findByLastName("Petrescu"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Bucuresti")
                                        .street("Calea Leului")
                                        .number(143)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("apoteca@gmail.com")
                                        .phoneNumber("0761134312")
                                        .build())
                        .build()
        );
        pharmacyRepository.save(
                Pharmacy.builder()
                        .name("Dr. Max")
                        .pharmacyOwner(pharmacyOwnerRepository.findByLastName("Petrescu"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Cluj")
                                        .street("Calea Tigrului")
                                        .number(143)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("dr.max@gmail.com")
                                        .phoneNumber("0761134377")
                                        .build())
                        .build()
        );
        pharmacyRepository.save(
                Pharmacy.builder()
                        .name("Sensiblu")
                        .pharmacyOwner(pharmacyOwnerRepository.findByLastName("Petrescu"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Iasi")
                                        .street("Alea Trandafirilor")
                                        .number(143)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("sensiblu@gmail.com")
                                        .phoneNumber("0761142312")
                                        .build())
                        .build()
        );
        pharmacyRepository.save(
                Pharmacy.builder()
                        .name("Farmnicol")
                        .pharmacyOwner(pharmacyOwnerRepository.findByLastName("Petrescu"))
                        .address(
                                Address.builder()
                                        .country("Romania")
                                        .city("Bucuresti")
                                        .street("Calea Iepurelui")
                                        .number(143)
                                        .build())
                        .contactData(
                                ContactData.builder()
                                        .email("farmnicol@gmail.com")
                                        .phoneNumber("0761324312")
                                        .build())
                        .build()
        );
    }

    private void loadAppointments() {
        appointmentRepository.save(
                Appointment.builder()
                        .patient(patientRepository.findByLastName("Nacu"))
                        .medicalOffice(medicalOfficeRepository.findByName("Cristea's Dentist Office"))
                        .date(LocalDateTime.now())
                        .description("Vreau sa-mi albesc dintii.")
                        .build()
        );
        appointmentRepository.save(
                Appointment.builder()
                        .patient(patientRepository.findByLastName("Nacu"))
                        .medicalOffice(medicalOfficeRepository.findByName("Cristea's Dentist Office"))
                        .date(LocalDateTime.now().plusHours(1L))
                        .description("Am o carie")
                        .build()
        );
        appointmentRepository.save(
                Appointment.builder()
                        .patient(patientRepository.findByLastName("Nacu"))
                        .medicalOffice(medicalOfficeRepository.findByName("Cristea's Dentist Office"))
                        .date(LocalDateTime.now().plusHours(2L))
                        .description("Am nevoie de aparat dental")
                        .build()
        );
    }

    private void loadRecipes() {
        recipeRepository.save(
                Recipe.builder()
                        .patient(patientRepository.findByLastName("Nacu"))
                        .description("Take 2 pills of paracetamol per day and one of aspirin when you wake up")
                        .medicines(Set.of(medicineRepository.findByName("Paracetamol"), medicineRepository.findByName("Aspirin")))
                        .build()
        );
        recipeRepository.save(
                Recipe.builder()
                        .patient(patientRepository.findByLastName("Nacu"))
                        .description("Take 2 pills of each per day. One in the morning, the other one at night.")
                        .medicines(Set.of(medicineRepository.findByName("Amoxacilina"), medicineRepository.findByName("Furazolidon"), medicineRepository.findByName("Fasconal")))
                        .build()
        );
        recipeRepository.save(
                Recipe.builder()
                        .patient(patientRepository.findByLastName("Nacu"))
                        .description("Take one per day after you eat")
                        .medicines(Set.of(medicineRepository.findByName("Amoxacilina")))
                        .build()
        );
    }

    private void loadMedicineStock() {
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Paracetamol"))
                        .pharmacy(pharmacyRepository.findByName("Catena"))
                        .amount(312)
                        .price(30.20)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Aspirin"))
                        .pharmacy(pharmacyRepository.findByName("Catena"))
                        .amount(1310)
                        .price(40)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Furazolidon"))
                        .pharmacy(pharmacyRepository.findByName("Catena"))
                        .amount(12)
                        .price(10.30)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Nospa"))
                        .pharmacy(pharmacyRepository.findByName("Catena"))
                        .amount(132)
                        .price(12.0)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Nurofen"))
                        .pharmacy(pharmacyRepository.findByName("Catena"))
                        .amount(11)
                        .price(6.20)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Fasconal"))
                        .pharmacy(pharmacyRepository.findByName("Catena"))
                        .amount(12)
                        .price(31.50)
                        .build()
        );

        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Aspirin"))
                        .pharmacy(pharmacyRepository.findByName("Sensiblu"))
                        .amount(31)
                        .price(28.90)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Paracetamol"))
                        .pharmacy(pharmacyRepository.findByName("Sensiblu"))
                        .amount(131)
                        .price(42.5)
                        .build()
        );
        medicineStockRepository.save(
                MedicineStock.builder()
                        .medicine(medicineRepository.findByName("Fasconal"))
                        .pharmacy(pharmacyRepository.findByName("Sensiblu"))
                        .amount(112)
                        .price(40.5)
                        .build()
        );
    }
}
