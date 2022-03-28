package presentation.controller;

import entity.User;
import service.OutsiderService;

public class RegisterController extends Controller {
    OutsiderService outsiderService;
    public RegisterController(LogInController prev){
        this.outsiderService= prev.getOutsiderService();
    }
    public void register(User credentials){
       outsiderService.register(credentials);
    }
}
