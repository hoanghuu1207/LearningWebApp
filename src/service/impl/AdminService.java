package service.impl;

import dao.impl.AdminDAO;
import model.AdminModel;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import service.I_AdminService;

public class AdminService implements I_AdminService {
    private AdminDAO adminDAO = new AdminDAO();
    @Override
    public AdminModel loginAdmin(AdminModel adminModel) {
        AdminModel adminModel1 = adminDAO.getUserByUsername(adminModel.getUsername());
        if (adminModel1 == null) {
            System.out.println("Username khong ton tai");

            return adminModel1;
        }

        if (!BCrypt.checkpw(adminModel.getPassword(), adminModel1.getPassword())) {
            System.out.println("Sai mat khau");

            return null;
        }

        return adminModel1;
    }
}
