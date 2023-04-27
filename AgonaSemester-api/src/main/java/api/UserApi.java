package api;

import dto.request.UserRequest;
import dto.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(tags = "Users | ������������", value = "������������")
@RequestMapping("/users")
public interface UserApi {

    @ApiOperation(value = "�������� ������������", nickname = "user-create", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������������ ������"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody UserRequest request);

    @ApiOperation(value = "���������� ������������", nickname = "user-update", response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������������ �������"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@PathVariable UUID userId, @RequestBody UserRequest request);

    @ApiOperation(value = "�������� ������������", nickname = "user-delete", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������������ �����"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUserById(@PathVariable UUID userId);

    @ApiOperation(value = "��������� ������������ �� ����", nickname = "user-by-id", response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������������ �������"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse getUserById(@PathVariable UUID userId);

    @ApiOperation(value = "��������� ������ ���� �������������", nickname = "user-get-all", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������ �������"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserResponse> getUsers();

    @ApiOperation(value = "��������� ������ ���� ���������� ����", nickname = "user-get-by-chat-id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������ �������"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @GetMapping("/chat/{chatID}")
    @ResponseStatus (HttpStatus.OK)
    List<UserResponse> getUsersByChatId(@PathVariable UUID chatID);

    @ApiOperation(value = "��������� ������ ����� �� ���� �����", nickname = "user-get-by-post-id", response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "������������ �������"),
            @ApiResponse(code = 400, message = "������ ���������")})
    @GetMapping("/post/{postId}")
    @ResponseStatus (HttpStatus.OK)
    UserResponse getUserByPostId(@PathVariable UUID postId);
}
