package fr.treeptik.controller;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.treeptik.model.Evaluation;
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

	public void mailRecapEvaluation(Evaluation evaluation) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("h.fontbonne@treeptik.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("rvfontbonne@hotmail.com"));
/*			message.setSubject(evaluation.getStagiaireSession().getId().getNom()
					+ " "
					+ evaluation.getStagiaireSession().getStagiaire().getPrenom()
					+ " a effectué l'évaluation de la session "
					+ evaluation.getStagiaireSession().getSession().getNom() + ".");

			message.setText("Bonjour,"
					+ evaluation.getStagiaireSession().getStagiaire().getNom() + " "
					+ evaluation.getStagiaireSession().getStagiaire().getPrenom()
					+ " a effectué l'évaluation :" + "\n-de la Formation : "
					+ evaluation.getStagiaireSession().getSession().getNom() + "."
					+ "\n-de la Session : "
					+ evaluation.getStagiaireSession().getSession().getNom() + "."
//					+ "\n-Animé par : " + evaluation.getStagiaireSession().getSession().getListFormations()
//					+ " " + evaluation + "."
//					+ "\n**************"
//					
					);*/
			/*
			 * + "\n\nVoici le récapitulatif de son évaluation:" +
			 * "\n\nComment avez-vous trouvé..."
			 * +"\n 1 - L'acceuil : "+evaluation.getAcceuil()+"/20"
			 * +"\n 2 - La salle de formation : "+evaluation.getSalle()+"/20"
			 * +"\n3 - Le matériel mis à disposition : "
			 * +evaluation.getMateriel()+"/20"
			 * +"\n4 - La qualité de la formation : "
			 * +evaluation.getQualFormation()+"/20"
			 * +"\n5 - Le rythme du cours : "+evaluation.getRythme()
			 * +"\n6 - Les compétences techniques des formateurs : "
			 * +evaluation.getTechnique()+"/20"
			 * +"\n7 - Les compétences pédagogiques des formateurs : "
			 * +evaluation.getPedagogie()+"/20"
			 * +"\n8 - La capacité d’écoute des formateurs : "
			 * +evaluation.getCapacite()+"/20"
			 * +"\n9 - Etaient ils assez disponibles : "
			 * +evaluation.getDisponibilite()+"/20"
			 * +"\n10 - Le programme était il adapté à votre niveau : "
			 * +evaluation.getProgramme()+"/20"
			 * +"\n11 - Le contenu des modules a t'il répondu à vos objectifs : "
			 * +evaluation.getContenu()+"/20"
			 * +"\n12 - La durée de la formation a t'elle été suffisante : "
			 * +evaluation.getDuree()
			 * +"\n13 - Votre objectif a t'il été atteint : "
			 * +evaluation.getObjectif() +
			 * "\n14 - Quels sont, selon vous, les aménagements à apporter au programme :"
			 * +"\n\t\t*Sujets à supprimer ou à modifier: "
			 * +"\n\t\t\t"+evaluation.getSujetSupprimer()
			 * +"\n\t\t**Sujets à approfondir ou à ajouter :"
			 * +"\n\t\t\t"+evaluation.getSujetApprofondir()
			 * +"\n15 - Quelle note attribuez-vous au formateur : "
			 * +evaluation.getNoteFormateur()+"/20"
			 * +"\n16 - Etes-vous intéressé(e) par d’autres stages de formation :"
			 * +evaluation.getAutreStage()
			 * +"\n\t\tSi oui, merci de préciser lesquels :"
			 * +"\n\t\t\t"+evaluation.getProjet() + "\n\nCordialement," +
			 * "\nL'équipe Treeptik.");
			 */
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}