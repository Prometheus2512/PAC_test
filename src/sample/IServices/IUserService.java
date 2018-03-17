package sample.IServices;

import sample.Entities.User;

public interface IUserService {
    /*public void addUser(User u);
    public void editUser(User u);
    public void deleteUser(User u);*/
    public User showUser(String username);
    public boolean addUser(User u);
    public String hashPassword(String password_plaintext);


        public User Login(String username, String password);
    public Boolean Authentificate(String username, String password);
}
