package fr.treeptik.controller;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;

import fr.treeptik.model.Evaluation;
import fr.treeptik.model.User;

@Stateless
public class SendTextMessage {

	@Resource(mappedName = "java:jboss/mail/gmail")
	Session session;
	static String CREATIONUSER = "Confirmation d'inscription pour l'évaluation de la formation";

	public void mailCreationUser(User user) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("h.fontbonne@treeptik.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject(CREATIONUSER);
			message.setText("Bonjour,Mr.(Mme)"
					+ user.getUsername()
					+ ","
					+ "\n\nVotre inscription pour évaluer la formation a été effectuée."
					+ "\nPour vous connectez, voici vos identifiants:"
					+ "\n email :"
					+ user.getEmail()
					+ "\n    mot de passe :"
					+ user.getPassword()
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
			message.setSubject(evaluation.getStagiaire().getNom()
					+ " "
					+ evaluation.getStagiaire().getPrenom()
					+ "a effectué l'évaluation de la session"
					+ evaluation.getSession().getNom()
					+ ".");
			
			message.setText("Bonjour,"+evaluation.getStagiaire().getNom()
					+ " "
					+ evaluation.getStagiaire().getPrenom()
					+ "a effectué l'évaluation de la session"
					+ evaluation.getSession().getNom()
					+ "."
					+ "\n\nVoici le récapitulatif de son evaluation:"
					+ "_n\nComment avez-vous trouvé..."
					+"\n 1 - L'acceuil : "+evaluation.getAcceuil()
					+"\n 2 - La salle de formation : "+evaluation.getSalle()
					+"\n3 - Le matériel mis à disposition : "+evaluation.getMateriel()
					+"\n4 - La qualité de la formation : "+evaluation.getSatisfait()
					+"\n5 - Le rythme du cours : "+evaluation.getRythme()
					+"\n6 - Les compétences techniques des formateurs : "+evaluation.getTechnique()
					+"\n7 - Les compétences pédagogiques des formateurs : "+evaluation.getPedagogie()
					+"\n8 - La capacité d’écoute des formateurs : "+evaluation.getCapacite()
					+"\n9 - Etaient ils assez disponibles : "+evaluation.getDisponibilite()
					+"\n10 - Le programme était il adapté à votre niveau : "+evaluation.getProgramme()
					+"\n11 - Le contenu des modules a t'il répondu à vos objectifs : "+evaluation.getContenu()
					+"\n12 - La durée de la formation a t'elle été suffisante : "+evaluation.getDuree()
					+"\n13 - Votre objectif a t'il été atteint : "+evaluation.getObjectif()
					+"\n14 - Quels sont, selon vous, les aménagements à apporter au programme :"
					+"\n\t\t*Sujets à supprimer ou à modifier: "
					+"\n\t\t\t"+evaluation.getSujetSupprimer()
					+"\n\t\t**Sujets à approfondir ou à ajouter :"
					+"\n\t\t\t"+evaluation.getSujetApprofondir()
					+"\n15 - Quelle note attribuez-vous au formateur : "+evaluation.getEvaluationGlobale()
					+"\n16 - Etes-vous intéressé (e) par d’autres stages de formation :"+evaluation.getAutreStage()
					+"\n\t\tSi oui, merci de préciser lesquels :"
					+"\n\t\t\t"+evaluation.getProjet()
					+ "\n\nCordialement," + "\nL'équipe Treeptik.");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}