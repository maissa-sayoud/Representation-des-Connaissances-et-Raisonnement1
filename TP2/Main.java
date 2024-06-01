import java.util.ArrayList;  
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.commons.syntax.Constant;
import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.commons.syntax.Sort;
import org.tweetyproject.logics.fol.parser.FolParser;
import org.tweetyproject.logics.fol.reasoner.FolReasoner;
import org.tweetyproject.logics.fol.reasoner.SimpleFolReasoner;
import org.tweetyproject.logics.fol.syntax.FolBeliefSet;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;


public class Main 
{
    public static void main(String[] args) throws ParserException, IOException { FolSignature sig = new FolSignature(true);

    // Ajout d'un tri (sort)
    Sort sortPlante = new Sort("Plante");
    Sort sortCouleur = new Sort("Couleur");
    sig.add(sortPlante, sortCouleur);

    // Ajout de constantes pour les plantes
    Constant constantRose = new Constant("rose", sortPlante);
    Constant constantTournesol = new Constant("tournesol", sortPlante);
    Constant constantMenthe = new Constant("menthe", sortPlante);

    // Ajout de constantes pour les couleurs
    Constant constantRouge = new Constant("rouge", sortCouleur);
    Constant constantJaune = new Constant("jaune", sortCouleur);
    Constant constantVert = new Constant("vert", sortCouleur);
    
    sig.add(constantRose, constantTournesol, constantMenthe, constantRouge, constantJaune, constantVert);

    // Ajout de prédicats
    List<Sort> predicateList = new ArrayList<Sort>();
    predicateList.add(sortPlante);
    Predicate p = new Predicate("Fleur", predicateList); // Prédicat Fleur(Plante)

    List<Sort> predicateList2 = new ArrayList<Sort>();
    predicateList2.add(sortPlante);
    predicateList2.add(sortCouleur);
    Predicate p2 = new Predicate("Couleur", predicateList2); // Prédicat Couleur(Plante, Couleur)

    sig.add(p, p2);

    // Affichage de la signature
    System.out.println("Signature: " + sig);

    // Exemple 2 : Utilisation de la signature pour créer des croyances
    FolParser parser = new FolParser();
    parser.setSignature(sig); // Utilisation de la signature définie ci-dessus
    FolBeliefSet bs = new FolBeliefSet();

    // Création de formules et ajout à l'ensemble de croyances
    FolFormula f1 = (FolFormula)parser.parseFormula("Fleur(rose)");
    FolFormula f2 = (FolFormula)parser.parseFormula("Couleur(rose, rouge)");
    FolFormula f3 = (FolFormula)parser.parseFormula("Fleur(tournesol)");
    FolFormula f4 = (FolFormula)parser.parseFormula("Couleur(tournesol, jaune)");
    FolFormula f5 = (FolFormula)parser.parseFormula("!Fleur(menthe)");
    FolFormula f6 = (FolFormula)parser.parseFormula("Couleur(menthe, vert)");
    bs.add(f1, f2, f3, f4, f5, f6);

    // Affichage de l'ensemble de croyances
    System.out.println("\nParsed BeliefBase: " + bs);

    // Exemple 3 : Utilisation d'un prouveur pour vérifier si diverses formules peuvent être déduites de la base de connaissances
    FolReasoner.setDefaultReasoner(new SimpleFolReasoner()); // Définition du prouveur par défaut
    FolReasoner prover = FolReasoner.getDefaultReasoner();

    // Vérification des requêtes
    System.out.println("rose est une fleur : " + prover.query(bs, (FolFormula)parser.parseFormula("Fleur(rose)")));
    System.out.println("la couleur de tournesol est jaune :  " + prover.query(bs, (FolFormula)parser.parseFormula("Couleur(tournesol, jaune)")));
    System.out.println("menthe est une fleur :  " + prover.query(bs, (FolFormula)parser.parseFormula("Fleur(menthe)")));
    System.out.println("la couleur de menthe est rouge : " + prover.query(bs, (FolFormula)parser.parseFormula("Couleur(menthe, rouge)"))); 
    }
}