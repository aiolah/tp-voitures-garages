package garages;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.PrintStream;
import java.util.*;


/**
 * Représente une voiture qui peut être stationnée dans des garages.
 */
@RequiredArgsConstructor
@ToString
public class Voiture {

	@Getter
	@NonNull
	private final String immatriculation;
	@ToString.Exclude // On ne veut pas afficher les stationnements dans toString
	private final List<Stationnement> myStationnements = new LinkedList<>();

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws IllegalStateException Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws IllegalStateException {
		// Et si la voiture est déjà dans un garage ?
		// S'il existe des stationnements
		if(myStationnements.size() > 0)
		{
			// Si le dernier stationnement est en cours
			if(myStationnements.getLast().estEnCours())
			{
				throw new IllegalStateException();
			}
		}

		// Si pas de stationnement en cours
		// alors on ajoute le stationnement à la liste de stationnements
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws IllegalStateException si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws IllegalStateException {
		// throw new UnsupportedOperationException("Pas encore implémenté");
		// TODO: Implémenter cette méthode
		// Trouver le dernier stationnement de la voiture
		Stationnement lastStationnement = myStationnements.getLast();

		// Si le dernier stationnement est fini donc n'est pas en cours
		if(!lastStationnement.estEnCours())
		{
			throw new IllegalStateException();
		}

		// Terminer ce stationnement
		lastStationnement.terminer();
	}

	/**
	 * Calcule l'ensemble des garages visités par cette voiture
	 * 
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
		Set<Garage> garages = new HashSet<>();

		for(int i = 0; i < myStationnements.size(); i++)
		{
			garages.add(myStationnements.get(i).getGarageVisite());
		}

		return garages;

		// throw new UnsupportedOperationException("Pas encore implémenté");
	}

	/**
	 * Détermine si la voiture est actuellement dans un garage
	 * 
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// TODO: Implémenter cette méthode
		// Vrai si il y a des stationnements et le dernier stationnement est en cours
		if(myStationnements.size() > 0 && myStationnements.getLast().estEnCours())
		{
			return true;
		}
		else
		{
			return false;
		}

		// throw new UnsupportedOperationException("Pas encore implémenté");
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * stationnements dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage(name=Universite Champollion Albi):
	 * 		Stationnement{ entree=13/11/2024, sortie=13/11/2024 }
	 * Garage(name=ISIS Castres):
	 * 		Stationnement{ entree=13/11/2024, sortie=13/11/2024 }
	 * 		Stationnement{ entree=13/11/2024, en cours }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out pour imprimer dans la
	 *            console)
	 */

	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode
		// throw new UnsupportedOperationException("Pas encore implémenté");
		// Utiliser les méthodes toString() de Garage et Stationnement
		String result = "";

		for(Garage garage: garagesVisites())
		{
			result += garage.toString();
			result += ":\n";

			for(Stationnement stationnement: myStationnements)
			{
				if(stationnement.getGarageVisite() == garage)
				{
					result += "\t";
					result += stationnement.toString();
					result += "\n";
				}

				if(stationnement == myStationnements.getLast())
				{
					result += "\n";
				}
			}
		}

		out.println(result);
	}

}
