package fr.treeptik.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.treeptik.model.Formateur;
import fr.treeptik.model.Formation;
import fr.treeptik.model.Resultat;
import fr.treeptik.model.Stagiaire;

@Stateless
public class SendTextMessage {

	@Resource(mappedName = "java:jboss/mail/gmail")
	Session session;
	static String CREATIONUSER = "Confirmation d'inscription pour l'évaluation de la formation Treeptik";

	private String MrMme(Stagiaire stagiaire) {
		if (stagiaire.getSexe().equals("Homme"))
			return "Mr.";
		if (stagiaire.getSexe().equals("Femme"))
			return "Mme";
		else
			return " ";
	}

	public void mailCreationUser(Stagiaire stagiaire) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("h.fontbonne@treeptik.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(stagiaire.getEmail()));
			message.setSubject(CREATIONUSER);
			message.setText("Bonjour "
					+ MrMme(stagiaire)
					+ " "
					+ stagiaire.getNom()
					+ ","
					+ "\n\nVotre inscription pour évaluer la formation a été effectuée."
					+ "\nPour vous connectez, voici vos identifiants:"
					+ "\n email :"
					+ stagiaire.getEmail()
					+ "\n    mot de passe :"
					+ stagiaire.getPassword()
					+ "\n\n"
					+ "Veuillez garder cet email, vos identifiants doivent être conservés."
					+ "\n\nCordialement," + "\nL'équipe Treeptik.");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String boucleRecapResultat(List<Resultat> listResultats) {
		StringBuilder recapBuilder = new StringBuilder();
		String recap;
		for (Resultat resultat : listResultats) {
			recapBuilder.append(resultat.getId().getQuestion().getLibelle()
					+ " : " + resultat.getNote() + "/4\n\n");
		}
		recap = recapBuilder.toString();

		return recap;
	}

	public String boucleFormateur(List<Formateur> listFormateurs) {
		StringBuilder recapBuilder = new StringBuilder();
		String recap;
		for (Formateur formateur : listFormateurs) {
			recapBuilder.append("Formateur :" + " : " + formateur.getNom()
					+ " " + formateur.getPrenom() + ".\n\n");
		}
		recap = recapBuilder.toString();

		return recap;
	}

	public String boucleFormation(List<Formation> listFormations) {
		StringBuilder recapBuilder = new StringBuilder();
		String recap;
		for (Formation formation : listFormations) {
			recapBuilder
					.append("la Formation :" + formation.getNom() + ".\n\n");
		}
		recap = recapBuilder.toString();

		return recap;
	}

	public void mailRecapEvaluation(List<Resultat> listResultat,
			List<Formateur> listFormateurs, List<Formation> listFormations) {
		try {
			Resultat resultat = new Resultat();
			resultat = listResultat.get(0);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("h.fontbonne@treeptik.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("rvfontbonne@hotmail.com"));
			message.setSubject(resultat.getId().getStagiaireSession().getId()
					.getStagiaire().getNom()
					+ " "
					+ resultat.getId().getStagiaireSession().getId()
							.getStagiaire().getPrenom()
					+ " a effectué l'évaluation de la session "
					+ resultat.getId().getStagiaireSession().getId()
							.getSession().getNom() + ".");

			message.setText("Bonjour,"
					+ resultat.getId().getStagiaireSession().getId()
							.getStagiaire().getNom()
					+ " "
					+ resultat.getId().getStagiaireSession().getId()
							.getStagiaire().getPrenom()
					+ " a effectué l'évaluation de la session "
					+ resultat.getId().getStagiaireSession().getId()
							.getSession().getNom() + ".\n" + "\n**************"
					+ "\n Composé de :\n" + boucleFormation(listFormations)
					// + "animé par :\n"+boucleFormateur(listFormateurs)
					+ "\n\nVoici le récapitulatif de son évaluation:\n\n"
					+ boucleRecapResultat(listResultat) +

					"\n\nCordialement," + "\nL'équipe Treeptik.");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}