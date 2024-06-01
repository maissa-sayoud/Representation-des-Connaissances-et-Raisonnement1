/*______________________________________________*/  

/*					BISMI ALLAH								*/
/*______________________________________________*/

/************************************************/
/*______________________________________________*/

/*		SAYOUD Maissa & BOULKABOUL Amira		*/
/*______________________________________________*/


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.commons.syntax.Constant;
import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.commons.syntax.RelationalFormula;
import org.tweetyproject.logics.commons.syntax.Sort;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;
import org.tweetyproject.logics.ml.parser.MlParser;
import org.tweetyproject.logics.ml.reasoner.SimpleMlReasoner;
import org.tweetyproject.logics.ml.syntax.MlBeliefSet;

public class LogiqueModale 
{

    public static void main(String[] args) throws ParserException, IOException 
    {
        // Création d'une signature de logique modale
        FolSignature sig = new FolSignature(true);

        // Ajout de tris (sorts) pour les plantes et les couleurs
        Sort sortPlante = new Sort("Plante");
        Sort sortCouleur = new Sort("Couleur");
        sig.add(sortPlante, sortCouleur);

        // Ajout de constantes pour les plantes
        Constant rose = new Constant("rose", sortPlante);
        Constant tournesol = new Constant("tournesol", sortPlante);
        Constant menthe = new Constant("menthe", sortPlante);

        // Ajout de constantes pour les couleurs
        Constant rouge = new Constant("rouge", sortCouleur);
        Constant jaune = new Constant("jaune", sortCouleur);
        Constant vert = new Constant("vert", sortCouleur);

        sig.add(rose, tournesol, menthe, rouge, jaune, vert);

        // Ajout de prédicats
        List<Sort> fleurSorts = new ArrayList<>();
        fleurSorts.add(sortPlante);
        Predicate Fleur = new Predicate("Fleur", fleurSorts);

        List<Sort> couleurSorts = new ArrayList<>();
        couleurSorts.add(sortPlante);
        couleurSorts.add(sortCouleur);
        Predicate Couleur = new Predicate("Couleur", couleurSorts);

        sig.add(Fleur, Couleur);

        // Création d'un ensemble de croyances modales
        MlBeliefSet bs = new MlBeliefSet();

        // Création d'un parseur pour analyser les formules modales
        MlParser parser = new MlParser();
        parser.setSignature(sig);

        // Ajout de formules modales à l'ensemble de croyances
        bs.add((RelationalFormula) parser.parseFormula("<>(Fleur(rose))")); // Il est possible que la rose soit une fleur
        //bs.add((RelationalFormula) parser.parseFormula("[](Couleur(tournesol, jaune))")); // Le tournesol est jaune dans tous les mondes possibles
        //bs.add((RelationalFormula) parser.parseFormula("<>(Fleur(tournesol))")); // Il est possible que le tournesol soit une fleur
        //bs.add((RelationalFormula) parser.parseFormula("[](Couleur(menthe, vert))")); // La menthe est verte dans tous les mondes possibles
        //bs.add((RelationalFormula) parser.parseFormula("<>(!Fleur(menthe))")); // Il est possible que la menthe ne soit pas une fleur
         
        
        
        // Affichage de l'ensemble de croyances modales
        System.out.println("Modal knowledge base: " + bs);

        
        // Création d'un prouveur modale
        SimpleMlReasoner reasoner = new SimpleMlReasoner();

        // Vérification des requêtes modales
        System.out.println("[](!Fleur(rose)): " + reasoner.query(bs, (FolFormula) parser.parseFormula("[](!Fleur(rose))")) + "\n"); // La rose n'est pas une fleur dans tous les mondes possibles
        //System.out.println("<>(Fleur(tournesol) && Couleur(tournesol, jaune)): " + reasoner.query(bs, (FolFormula) parser.parseFormula("<>(Fleur(tournesol) && Couleur(tournesol, jaune))")) + "\n"); // Il est possible que le tournesol soit une fleur et soit jaune
        //System.out.println("[](Fleur(rose) || Fleur(menthe)): " + reasoner.query(bs, (FolFormula) parser.parseFormula("[](Fleur(rose) || Fleur(menthe))")) + "\n"); // La rose ou la menthe est une fleur dans tous les mondes possibles
        //System.out.println("<>(!Couleur(rose, rouge)): " + reasoner.query(bs, (FolFormula) parser.parseFormula("<>(!Couleur(rose, rouge))")) + "\n"); // Il est possible que la rose ne soit pas rouge
        
    
    }
}
