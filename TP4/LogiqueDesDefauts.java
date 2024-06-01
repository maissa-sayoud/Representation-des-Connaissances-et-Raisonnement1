/*______________________________________________*/ 
/*			      BISMI ALLAH					*/
/*______________________________________________*/

/************************************************/
/*______________________________________________*/

/*		SAYOUD Maissa & BOULKABOUL Amira		*/
/*______________________________________________*/

import orbital.logic.functors.Default;
import orbital.logic.imp.DefaultReasoner;
import orbital.logic.sign.Signature;
import orbital.logic.template.DefaultKnowledgeBase;

public class LogiqueDesDefauts 
{
    public static void main(String[] args) 
    {
        try {
            // Création d'une signature
            Signature signature = new Signature();
            signature.add("rose", "tournesol", "menthe", "rouge", "jaune", "vert");
            signature.add("Fleur", "Couleur");

            // Création d'une base de connaissances par défaut
            DefaultKnowledgeBase kb = new DefaultKnowledgeBase(signature);

            // Ajout des défauts (pré-requis : exceptions / conclusion)
            kb.add(new Default(
                "Fleur(rose)",  // Pré-requis
                "",            // Exceptions (vide dans ce cas)
                "Fleur(rose)"  // Conclusion
            ));
            kb.add(new Default(
                "Couleur(rose, rouge)",  // Pré-requis
                "",                      // Exceptions (vide dans ce cas)
                "Couleur(rose, rouge)"   // Conclusion
            ));

            kb.add(new Default(
                "Fleur(tournesol)",
                "",
                "Fleur(tournesol)"
            ));
            kb.add(new Default(
                "Couleur(tournesol, jaune)",
                "",
                "Couleur(tournesol, jaune)"
            ));

            kb.add(new Default(
                "Fleur(menthe)",
                "",
                "Fleur(menthe)"
            ));
            kb.add(new Default(
                "Couleur(menthe, vert)",
                "",
                "Couleur(menthe, vert)"
            ));

            // Affichage de la base de connaissances
            System.out.println("Base de connaissances par défaut:");
            System.out.println(kb);

            // Vérification des requêtes
            DefaultReasoner reasoner = new DefaultReasoner();

            System.out.println("Fleur(rose) ? " + reasoner.query(kb, "Fleur(rose)"));
            System.out.println("Couleur(rose, rouge) ? " + reasoner.query(kb, "Couleur(rose, rouge)"));
            System.out.println("Fleur(tournesol) ? " + reasoner.query(kb, "Fleur(tournesol)"));
            System.out.println("Couleur(tournesol, jaune) ? " + reasoner.query(kb, "Couleur(tournesol, jaune)"));
            System.out.println("Fleur(menthe) ? " + reasoner.query(kb, "Fleur(menthe)"));
            System.out.println("Couleur(menthe, vert) ? " + reasoner.query(kb, "Couleur(menthe, vert)"));

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
