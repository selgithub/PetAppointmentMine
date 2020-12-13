package it.unical.mat.asd.vetappointment;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("it.unical.mat.asd.vetappointment");

        noClasses()
            .that()
                .resideInAnyPackage("it.unical.mat.asd.vetappointment.service..")
            .or()
                .resideInAnyPackage("it.unical.mat.asd.vetappointment.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..it.unical.mat.asd.vetappointment.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
