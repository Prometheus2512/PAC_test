package sample.IServices;

import sample.Entities.User;

public interface IUserService {
    public void editUser(User u);
    public void deleteUser(int id);
    public User showUser(String username);

    public boolean addUser(User u);

    public String hashPassword(String password_plaintext);

    public User Login(String username, String password);

    public Boolean Authentificate(String username, String password);
}
