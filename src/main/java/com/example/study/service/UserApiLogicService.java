package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserAPiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserAPiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserAPiRequest> request) {

        // 1. request data

        UserAPiRequest userAPiRequest = request.getData();

        // 2. user 생성

        User user = User.builder()
                .account(userAPiRequest.getAccount())
                .password(userAPiRequest.getPassword())
                .status("REGISTERD")
                .phoneNumber(userAPiRequest.getPhoneNumber())
                .email(userAPiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        return userRepository.findById(id)
                .map(user -> response(user)) // 다른 return 형태로 바꿈
                .orElseGet(
                        ()-> Header.ERROR("데이터 없음")
                );


        // user -> userApiResponse return


    }

    @Override
    public Header<UserApiResponse> update(Header<UserAPiRequest> request) {

        // 1. data

        UserAPiRequest userAPiRequest = request.getData();

        // 2. id -> user 데이터를 찾고

        Optional<User> optional = userRepository.findById(userAPiRequest.getId());

        return optional.map(user -> {
            // 3. data -> update
            user.setAccount(userAPiRequest.getAccount())
                    .setPassword(userAPiRequest.getPassword())
                    .setStatus(userAPiRequest.getStatus())
                    .setPhoneNumber(userAPiRequest.getPhoneNumber())
                    .setEmail(userAPiRequest.getEmail())
                    .setRegisteredAt(userAPiRequest.getRegisteredAt())
                    .setUnregisteredAt(userAPiRequest.getUnregisteredAt());
            return user;

            // 4. userApiResponse

        })
                .map(user -> userRepository.save(user)) // update -> newUser
                .map(updateUser -> response(updateUser))    //  userApiResponse
                .orElseGet(
                        ()-> Header.ERROR("데이터 없음")
                );

    }

    @Override
    public Header delete(Long id) {
        // 1. id ->  repository -> data

        Optional<User> optional = userRepository.findById(id);

        // 2. repository -> delete

        return optional.map(user -> {
            userRepository.delete(user);

            return Header.OK();

        })
                .orElseGet(
                        ()-> Header.ERROR("데이터 없음")
                );


        // 3. response return


    }

    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return

        return Header.OK(userApiResponse);


    }
}
