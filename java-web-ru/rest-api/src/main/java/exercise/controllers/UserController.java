package exercise.controllers;

import io.javalin.core.validation.BodyValidator;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        Long userId = Long.parseLong(id);

        User user = new QUser()
                .id.equalTo(userId)
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(obj -> !obj.getFirstName().isEmpty(), "The firstName should not be empty")
                .check(obj -> !obj.getLastName().isEmpty(), "The lastName should not be empty")
                .check(obj -> EmailValidator.getInstance().isValid(obj.getEmail()), "The email should be valid")
                .check(obj -> obj.getPassword().length() > 3, "Password must be at least 4 characters long")
                .check(obj -> StringUtils.isNumeric(obj.getPassword()), "The password must contain only digits")
                .get();

        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);

        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        Long userId = Long.parseLong(id);

        new QUser()
                .id.equalTo(userId)
                .delete();
        // END
    };
}
