package mail.service.impl;

import mail.dao.face.MailDao;
import mail.dao.impl.MailDaoImpl;
import mail.service.face.MailService;

public class MailServiceImpl implements MailService{

	MailDao maildao = new MailDaoImpl();
	
	@Override
	public boolean confirm(String email) {

		return 	maildao.confirm(email);
	}

}
