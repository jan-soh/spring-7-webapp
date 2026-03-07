package guru.springframework.spring7webapp.bootstrap;

import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("12345678");

        Book noEjb = new Book();
        noEjb.setTitle("J2EE Development without EJB");
        noEjb.setIsbn("987654");


        Author ericSaved = authorRepository.save(eric);
        Author rodSaved = authorRepository.save(rod);
        Book dddSaved = bookRepository.save(ddd);
        dddSaved.getAuthors().add(ericSaved);
        Book noEjbSaved = bookRepository.save(noEjb);
        noEjbSaved.getAuthors().add(rodSaved);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEjbSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        Publisher john = new Publisher();
        john.setPublisherName("Jooohhn");
        john.setAddress("Address of J");
        john.setCity("City of J");
        john.setState("State of J");
        john.setZip("ZIP of J");

        Publisher savedPublisher = publisherRepository.save(john);
        dddSaved.setPublisher(savedPublisher);

        bookRepository.save(dddSaved);
        bookRepository.save(noEjbSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Pub Count: " + publisherRepository.count());
    }
}
