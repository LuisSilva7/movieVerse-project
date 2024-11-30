package org.movieverse.movieverse_backend.config;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.model.Movie;
import org.movieverse.movieverse_backend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class MoviesConfig implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Optional<Movie> movieFromBd = movieRepository.findById(1L);

        movieFromBd.ifPresentOrElse(
                movie -> System.out.println("Movies already exist!"),
                () -> {
                    Movie movie0 = new Movie();
                    movie0.setImage("jumanji-movie.webp");
                    movie0.setImageVertical("jumanji-vertical.jpg");
                    movie0.setName("Jumanji");
                    movie0.setDescription("A group of teenagers get sucked into a magical video game, where they must complete dangerous quests to return to the real world. Each player takes on a different avatar with unique skills.");
                    movie0.setType("Comedy");
                    movie0.setPrice(BigDecimal.valueOf(14.99));
                    movie0.setDuration(119);
                    movie0.setRating(7.8);
                    movie0.setStripeId("price_1PWge0DCoRbghNPDKaXyW6HP");

                    movieRepository.save(movie0);

                    Movie movie1 = new Movie();
                    movie1.setImage("red-notice-movie.jpg");
                    movie1.setImageVertical("red-notice-vertical.jpg");
                    movie1.setName("Red Notice");
                    movie1.setDescription("An Interpol agent tracks the world's most wanted art thief. This high-stakes action-comedy features thrilling chases, unexpected alliances, and a series of clever heists.");
                    movie1.setType("Action");
                    movie1.setPrice(BigDecimal.valueOf(19.99));
                    movie1.setDuration(118);
                    movie1.setRating(6.5);
                    movie1.setStripeId("price_1PWgeLDCoRbghNPDnFCzai34");
                    movieRepository.save(movie1);

                    Movie movie2 = new Movie();
                    movie2.setImage("the-maze-runner-movie.webp");
                    movie2.setImageVertical("the-maze-runner-vertical.jpg");
                    movie2.setName("The Maze Runner");
                    movie2.setDescription("A young man wakes up in a maze with no memory of who he is. Along with other boys, he must navigate the dangerous labyrinth and uncover the truth about their captivity.");
                    movie2.setType("Action");
                    movie2.setPrice(BigDecimal.valueOf(12.99));
                    movie2.setDuration(113);
                    movie2.setRating(7.1);
                    movie2.setStripeId("price_1PWgecDCoRbghNPDn7OwskiS");
                    movieRepository.save(movie2);

                    Movie movie3 = new Movie();
                    movie3.setImage("twilight-movie.webp");
                    movie3.setImageVertical("twilight-vertical.jpg");
                    movie3.setName("Twilight");
                    movie3.setDescription("A teenage girl risks everything when she falls in love with a vampire. Their forbidden romance faces numerous challenges from rival supernatural beings and her own mortality.");
                    movie3.setType("Romance");
                    movie3.setPrice(BigDecimal.valueOf(8.99));
                    movie3.setDuration(122);
                    movie3.setRating(5.2);
                    movie3.setStripeId("price_1PWggcDCoRbghNPDU45zjM1h");
                    movieRepository.save(movie3);

                    Movie movie4 = new Movie();
                    movie4.setImage("purple-hearts-movie.jpg");
                    movie4.setImageVertical("purple-hearts-vertical.jpg");
                    movie4.setName("Purple Hearts");
                    movie4.setDescription("A military romance story with heart and drama. A young couple faces the challenges of love and separation while navigating the trials of military life and personal sacrifice.");
                    movie4.setType("Romance");
                    movie4.setPrice(BigDecimal.valueOf(9.49));
                    movie4.setDuration(115);
                    movie4.setRating(6.8);
                    movie4.setStripeId("price_1PWggtDCoRbghNPD0mOtL92Q");
                    movieRepository.save(movie4);

                    Movie movie5 = new Movie();
                    movie5.setImage("venom-movie.webp");
                    movie5.setImageVertical("venom-vertical.jpg");
                    movie5.setName("Venom");
                    movie5.setDescription("A journalist becomes the host of an alien symbiote. As they merge, he gains incredible powers but struggles to control the entity within, leading to a battle for survival.");
                    movie5.setType("Action");
                    movie5.setPrice(BigDecimal.valueOf(17.99));
                    movie5.setDuration(112);
                    movie5.setRating(6.7);
                    movie5.setStripeId("price_1PWghRDCoRbghNPDFZvDMlxZ");
                    movieRepository.save(movie5);

                    Movie movie6 = new Movie();
                    movie6.setImage("bird-box-movie.jpg");
                    movie6.setImageVertical("bird-box-vertical.jpg");
                    movie6.setName("Bird Box");
                    movie6.setDescription("A mysterious force decimates the population. Survivors must navigate a perilous journey blindfolded to avoid seeing a deadly phenomenon that drives people to insanity and death.");
                    movie6.setType("Horror");
                    movie6.setPrice(BigDecimal.valueOf(13.99));
                    movie6.setDuration(124);
                    movie6.setRating(6.6);
                    movie6.setStripeId("price_1PWgi1DCoRbghNPDcRktfGFk");
                    movieRepository.save(movie6);

                    Movie movie7 = new Movie();
                    movie7.setImage("mea-culpa-movie.jpg");
                    movie7.setImageVertical("mea-culpa-vertical.jpg");
                    movie7.setName("Mea Culpa");
                    movie7.setDescription("A touching romance with unexpected twists. Two people find love and redemption as they navigate through their complicated pasts and the unforeseen challenges that come their way.");
                    movie7.setType("Romance");
                    movie7.setPrice(BigDecimal.valueOf(11.99));
                    movie7.setDuration(110);
                    movie7.setRating(7.0);
                    movie7.setStripeId("price_1PWgiKDCoRbghNPDZQs7bkJW");
                    movieRepository.save(movie7);

                    Movie movie8 = new Movie();
                    movie8.setImage("top-gun-maverick-movie.webp");
                    movie8.setImageVertical("top-gun-maverick-vertical.jpg");
                    movie8.setName("Top Gun: Maverick");
                    movie8.setDescription("A seasoned fighter pilot takes to the skies once more. Facing new threats and training a new generation of aviators, he must confront his past and redefine his future.");
                    movie8.setType("War");
                    movie8.setPrice(BigDecimal.valueOf(15.99));
                    movie8.setDuration(130);
                    movie8.setRating(8.3);
                    movie8.setStripeId("price_1PWgihDCoRbghNPD7q5VuZIC");
                    movieRepository.save(movie8);

                    Movie movie9 = new Movie();
                    movie9.setImage("the-notebook-movie.webp");
                    movie9.setImageVertical("the-notebook-vertical.jpg");
                    movie9.setName("The Notebook");
                    movie9.setDescription("A timeless love story based on the best-selling novel. The film chronicles the enduring romance between a young couple from different social backgrounds, spanning decades of their lives.");
                    movie9.setType("Nostalgic");
                    movie9.setPrice(BigDecimal.valueOf(12.99));
                    movie9.setDuration(123);
                    movie9.setRating(7.9);
                    movie9.setStripeId("price_1PWgj1DCoRbghNPDO1GfBKld");
                    movieRepository.save(movie9);

                    Movie movie10 = new Movie();
                    movie10.setImage("little-women-movie.webp");
                    movie10.setImageVertical("little-women-vertical.jpg");
                    movie10.setName("Little Women");
                    movie10.setDescription("The classic story of four sisters growing up during the Civil War. Each sister follows her own path, balancing personal dreams with the pressures and expectations of society.");
                    movie10.setType("Nostalgic");
                    movie10.setPrice(BigDecimal.valueOf(10.99));
                    movie10.setDuration(135);
                    movie10.setRating(8.1);
                    movie10.setStripeId("price_1PWgjUDCoRbghNPD3clxhX3b");
                    movieRepository.save(movie10);

                    Movie movie11 = new Movie();
                    movie11.setImage("mad-max-movie.webp");
                    movie11.setImageVertical("mad-max-vertical.jpg");
                    movie11.setName("Mad Max: Fury Road");
                    movie11.setDescription("A post-apocalyptic action film with intense car chases. Max teams up with Furiosa to escape a tyrannical warlord and his army, racing across a barren wasteland in a fight for survival.");
                    movie11.setType("Action");
                    movie11.setPrice(BigDecimal.valueOf(16.99));
                    movie11.setDuration(120);
                    movie11.setRating(8.6);
                    movie11.setStripeId("price_1PWgjtDCoRbghNPDuVM3ZjO6");
                    movieRepository.save(movie11);

                    Movie movie12 = new Movie();
                    movie12.setImage("smile-movie.webp");
                    movie12.setImageVertical("smile-vertical.jpg");
                    movie12.setName("Smile");
                    movie12.setDescription("A horror film that will keep you on the edge of your seat. After witnessing a traumatic event, a therapist must navigate through a series of frightening occurrences to uncover the truth.");
                    movie12.setType("Horror");
                    movie12.setPrice(BigDecimal.valueOf(14.49));
                    movie12.setDuration(107);
                    movie12.setRating(6.4);
                    movie12.setStripeId("price_1PWglZDCoRbghNPDgOVLQoqA");
                    movieRepository.save(movie12);

                    Movie movie13 = new Movie();
                    movie13.setImage("dune-movie.webp");
                    movie13.setImageVertical("dune-vertical.jpg");
                    movie13.setName("Dune");
                    movie13.setDescription("A young nobleman becomes the ruler of a dangerous desert planet. He must navigate political intrigue, fierce battles, and ancient prophecies to secure his family's legacy and the future of the galaxy.");
                    movie13.setType("Action");
                    movie13.setPrice(BigDecimal.valueOf(18.99));
                    movie13.setDuration(155);
                    movie13.setRating(8.2);
                    movie13.setStripeId("price_1PWglsDCoRbghNPDYZgDi9ei");
                    movieRepository.save(movie13);

                    Movie movie14 = new Movie();
                    movie14.setImage("annabelle-creation-movie.webp");
                    movie14.setImageVertical("annabelle-creation-vertical.jpg");
                    movie14.setName("Annabelle: Creation");
                    movie14.setDescription("The origins of the terrifying Annabelle doll. A dollmaker and his wife welcome a nun and several orphans into their home, only to become the target of the possessed creation.");
                    movie14.setType("Horror");
                    movie14.setPrice(BigDecimal.valueOf(13.49));
                    movie14.setDuration(109);
                    movie14.setRating(6.5);
                    movie14.setStripeId("price_1PWgmGDCoRbghNPDO74cKErb");
                    movieRepository.save(movie14);

                    Movie movie15 = new Movie();
                    movie15.setImage("orphan-movie.webp");
                    movie15.setImageVertical("orphan-vertical.jpg");
                    movie15.setName("Orphan");
                    movie15.setDescription("A couple adopts a mysterious girl with a troubling past. As strange events unfold, they discover the dark secrets she harbors, leading to a shocking revelation that changes their lives forever.");
                    movie15.setType("Horror");
                    movie15.setPrice(BigDecimal.valueOf(12.49));
                    movie15.setDuration(123);
                    movie15.setRating(7.0);
                    movie15.setStripeId("price_1PWgmhDCoRbghNPD5LpypCqp");
                    movieRepository.save(movie15);

                    Movie movie16 = new Movie();
                    movie16.setImage("luther-the-fallen-sun-movie.jpg");
                    movie16.setImageVertical("luther-the-fallen-sun-vertical.jpg");
                    movie16.setName("Luther: The Fallen Sun");
                    movie16.setDescription("A gripping action movie featuring a relentless detective. As he tracks down a ruthless serial killer, he battles his own demons and uncovers a conspiracy that threatens the entire city.");
                    movie16.setType("Action");
                    movie16.setPrice(BigDecimal.valueOf(19.49));
                    movie16.setDuration(129);
                    movie16.setRating(7.5);
                    movie16.setStripeId("price_1PWgn6DCoRbghNPDVOOKW9Wn");
                    movieRepository.save(movie16);

                    Movie movie17 = new Movie();
                    movie17.setImage("beautiful-life-movie.jpg");
                    movie17.setImageVertical("beautiful-life-vertical.jpg");
                    movie17.setName("A Beautiful Life");
                    movie17.setDescription("A romance film that explores life's beautiful moments. Two souls find each other and navigate the complexities of love, facing both joyous and heartbreaking experiences that shape their relationship.");
                    movie17.setType("Romance");
                    movie17.setPrice(BigDecimal.valueOf(10.99));
                    movie17.setDuration(116);
                    movie17.setRating(7.2);
                    movie17.setStripeId("price_1PWgncDCoRbghNPDkH2vKNML");
                    movieRepository.save(movie17);

                    Movie movie18 = new Movie();
                    movie18.setImage("your-place-or-mine-movie.jpg");
                    movie18.setImageVertical("your-place-or-mine-vertical.jpg");
                    movie18.setName("Your Place or Mine");
                    movie18.setDescription("A light-hearted romantic comedy. Two best friends with contrasting personalities swap homes for a week, leading to humorous situations and unexpected discoveries about each other and themselves.");
                    movie18.setType("Romance");
                    movie18.setPrice(BigDecimal.valueOf(12.99));
                    movie18.setDuration(110);
                    movie18.setRating(6.9);
                    movie18.setStripeId("price_1PWfnYDCoRbghNPDp3cGq1nb");
                    movieRepository.save(movie18);

                    Movie movie19 = new Movie();
                    movie19.setImage("blended-movie.webp");
                    movie19.setImageVertical("blended-vertical.jpg");
                    movie19.setName("Blended");
                    movie19.setDescription("A family comedy about two single parents on a disastrous vacation. As they navigate through hilarious mishaps and misunderstandings, they slowly begin to realize they might just be perfect for each other.");
                    movie19.setType("Comedy");
                    movie19.setPrice(BigDecimal.valueOf(11.49));
                    movie19.setDuration(117);
                    movie19.setRating(6.3);
                    movie19.setStripeId("price_1PWgoLDCoRbghNPDXfdkpdIr");
                    movieRepository.save(movie19);

                    Movie movie20 = new Movie();
                    movie20.setImage("family-switch-movie.jpg");
                    movie20.setImageVertical("family-switch-vertical.jpg");
                    movie20.setName("Family Switch");
                    movie20.setDescription("A hilarious family comedy about switching places. Parents and their teenage kids wake up in each other's bodies, leading to a series of funny and insightful experiences that bring them closer together.");
                    movie20.setType("Comedy");
                    movie20.setPrice(BigDecimal.valueOf(10.99));
                    movie20.setDuration(105);
                    movie20.setRating(5.8);
                    movie20.setStripeId("price_1PWgoeDCoRbghNPD6jEb6uIT");
                    movieRepository.save(movie20);

                    Movie movie21 = new Movie();
                    movie21.setImage("madagascar-movie.webp");
                    movie21.setImageVertical("madagascar-vertical.jpg");
                    movie21.setName("Madagascar");
                    movie21.setDescription("A group of zoo animals find themselves in the wild. From the concrete jungle of New York to the actual jungles of Madagascar, they must learn to survive and embrace their true nature.");
                    movie21.setType("Comedy");
                    movie21.setPrice(BigDecimal.valueOf(14.49));
                    movie21.setDuration(86);
                    movie21.setRating(6.9);
                    movie21.setStripeId("price_1PWgouDCoRbghNPDMFRyLJEu");
                    movieRepository.save(movie21);

                    Movie movie22 = new Movie();
                    movie22.setImage("about-time-movie.webp");
                    movie22.setImageVertical("about-time-vertical.jpg");
                    movie22.setName("About Time");
                    movie22.setDescription("A touching romance with a time travel twist. A young man discovers he can travel through time and uses his ability to improve his love life and mend broken relationships with those he cares about.");
                    movie22.setType("Romance");
                    movie22.setPrice(BigDecimal.valueOf(13.99));
                    movie22.setDuration(123);
                    movie22.setRating(7.8);
                    movie22.setStripeId("price_1PWgpDDCoRbghNPD0zoBJKva");
                    movieRepository.save(movie22);

                    Movie movie23 = new Movie();
                    movie23.setImage("bad-neighbours-movie.webp");
                    movie23.setImageVertical("bad-neighbours-vertical.jpg");
                    movie23.setName("Bad Neighbours");
                    movie23.setDescription("A comedy about the clash between a young couple and a rowdy fraternity. The couple must navigate pranks, parties, and escalating antics to reclaim peace in their once quiet neighborhood.");
                    movie23.setType("Comedy");
                    movie23.setPrice(BigDecimal.valueOf(9.99));
                    movie23.setDuration(97);
                    movie23.setRating(6.3);
                    movie23.setStripeId("price_1PWg0FDCoRbghNPDW8g553VS");
                    movieRepository.save(movie23);

                    Movie movie24 = new Movie();
                    movie24.setImage("world-war-z-movie.webp");
                    movie24.setImageVertical("world-war-z-vertical.jpg");
                    movie24.setName("World War Z");
                    movie24.setDescription("A thrilling story of survival during a zombie apocalypse. As the world falls into chaos, a former UN investigator races against time to find the source of the outbreak and save humanity.");
                    movie24.setType("War");
                    movie24.setPrice(BigDecimal.valueOf(16.49));
                    movie24.setDuration(116);
                    movie24.setRating(7.0);
                    movie24.setStripeId("price_1PWgpaDCoRbghNPDd7ZeKjBr");
                    movieRepository.save(movie24);

                    Movie movie25 = new Movie();
                    movie25.setImage("blood-and-gold-movie.jpg");
                    movie25.setImageVertical("blood-and-gold-vertical.jpg");
                    movie25.setName("Blood & Gold");
                    movie25.setDescription("An intense war drama with gripping action. Amidst the brutality of WWII, a group of soldiers embarks on a perilous mission to uncover hidden treasures and navigate treacherous alliances.");
                    movie25.setType("War");
                    movie25.setPrice(BigDecimal.valueOf(18.49));
                    movie25.setDuration(119);
                    movie25.setRating(7.3);
                    movie25.setStripeId("price_1PWgq7DCoRbghNPDPbGC2sIL");
                    movieRepository.save(movie25);

                    Movie movie26 = new Movie();
                    movie26.setImage("my-girl-movie.webp");
                    movie26.setImageVertical("my-girl-vertical.jpg");
                    movie26.setName("My Girl");
                    movie26.setDescription("A heartwarming coming-of-age story. A young girl faces the trials of growing up, dealing with her first experiences of love, loss, and the complexities of family life.");
                    movie26.setType("Nostalgic");
                    movie26.setPrice(BigDecimal.valueOf(12.49));
                    movie26.setDuration(102);
                    movie26.setRating(7.5);
                    movie26.setStripeId("price_1PWgqYDCoRbghNPDeEguejas");
                    movieRepository.save(movie26);

                    Movie movie27 = new Movie();
                    movie27.setImage("company-of-heroes-movie.webp");
                    movie27.setImageVertical("company-of-heroes-vertical.jpg");
                    movie27.setName("Company of Heroes");
                    movie27.setDescription("A war film about a group of soldiers on a dangerous mission. Behind enemy lines, they must uncover a secret weapon that could turn the tide of the war and ensure their survival.");
                    movie27.setType("War");
                    movie27.setPrice(BigDecimal.valueOf(14.99));
                    movie27.setDuration(100);
                    movie27.setRating(6.4);
                    movie27.setStripeId("price_1PWgqoDCoRbghNPDTlBhp52Y");
                    movieRepository.save(movie27);

                    Movie movie28 = new Movie();
                    movie28.setImage("he-is-all-that-movie.jpg");
                    movie28.setImageVertical("he-is-all-that-vertical.jpg");
                    movie28.setName("He's All That");
                    movie28.setDescription("A modern take on a classic high school romance. A popular girl accepts a challenge to transform a socially awkward boy into prom king, leading to unexpected outcomes and heartfelt moments.");
                    movie28.setType("Nostalgic");
                    movie28.setPrice(BigDecimal.valueOf(13.49));
                    movie28.setDuration(91);
                    movie28.setRating(5.3);
                    movie28.setStripeId("price_1PWgrHDCoRbghNPDtuKUeK1X");
                    movieRepository.save(movie28);

                    Movie movie29 = new Movie();
                    movie29.setImage("blue-lagoon-movie.webp");
                    movie29.setImageVertical("blue-lagoon-vertical.jpg");
                    movie29.setName("Blue Lagoon");
                    movie29.setDescription("A romantic adventure of two young lovers stranded on an island. They must learn to survive and navigate the complexities of growing up in isolation, forming a deep bond with one another.");
                    movie29.setType("Romance");
                    movie29.setPrice(BigDecimal.valueOf(11.99));
                    movie29.setDuration(104);
                    movie29.setRating(6.2);
                    movie29.setStripeId("price_1PWgraDCoRbghNPDmymeCGPG");
                    movieRepository.save(movie29);
                }
        );
    }
}
