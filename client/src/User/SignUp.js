import React, { useState, useEffect, useRef } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";

import Header from "../Common/Header";
import styles from "./SignUp.module.css";

import * as yup from "yup";

function SignUp() {
    const [phone, setPhone] = useState("");
    const [si, setSi] = useState("");
    const [gu, setGu] = useState("");
    const [dong, setDong] = useState("");
    let siList = [];
    let guList = [];
    let dongList = [];
    const phoneRef = useRef();

    useEffect(() => {
        getSi();
    })

    const formSchema = yup.object({
        id: yup
            .string()
            .required("id를 입력해주세요.")
            .min(3, "3자 이상 입력해주세요."),
        userName: yup
            .string()
            .required("이름을 입력해주세요.")
            .min(2, "2자 이상 입력해주세요."),
        nickName: yup
            .string()
            .required("닉네임을 입력해주세요.")
            .min(3, "3자 이상 입력해주세요."),
        email: yup
            .string()
            .required("이메일을 입력해주세요.")
            .email("올바른 이메일 형식이 아닙니다."),
        password: yup
            .string()
            .required("영문, 숫자 포함 8자리 이상을 입력해주세요.")
            .min(8, "8자 이상 입력해주세요.")
            .matches(
                /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}$/,
                "영문 숫자포함 8자리를 입력해주세요."
            ),
        passwordConfirm: yup
            .string()
            .oneOf([yup.ref("password")], "비밀번호가 다릅니다."),
    });

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm({
        mode: "onChange",
        resolver: yupResolver(formSchema),
    });

    const handleSi = (e) => {
        console.log(e.target.value);
        setSi(e.target.value);
    }

    const handlePhone = (e) => {
        const value = phoneRef.current.value.replace(/\D+/g, "");
        const numberLength = 11;
        let result = "";

        for (let i = 0; i < value.length && i < numberLength; i++) {
            switch (i) {
                case 3:
                    result += "-";
                    break;
                case 7:
                    result += "-";
                    break;
                default:
                    break;
            }
            result += value[i];
        }
        phoneRef.current.value = result;

        setPhone(e.target.value);
    }

    const onSubmit = (data) => {
        console.log(data);
        let url = process.env.REACT_APP_SERVER_URL + "/signup";
        axios.post(url, {
            id: data.id,
            userName: data.userName,
            nickName: data.nickName,
            phone: phone,
            email: data.email,
            region: dong,
            pwd: data.password,
        })
    }

    const getSi = () => {
        console.log("get-si");
        let url = process.env.REACT_APP_SERVER_URL + "/si";
        axios.get(url)
            .then(function (response) {
                siList = response.data;
                console.log(siList);
            })
    }

    const getGu = () => {
        let url = process.env.REACT_APP_SERVER_URL + "/gu/" + si;
        axios.get(url)
            .then(function (response) {
                console.log(response);
            })
    }

    const getDong = () => {
        let url = process.env.REACT_APP_SERVER_URL + "/dong/" + gu;
        axios.get(url)
            .then(function (response) {
                console.log(response);
            })
    }

    return(
        <div>
            <Header />
            <div id={styles["outer"]}>
                <form onSubmit={handleSubmit} className={styles["form-wrapper"]}>
                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>ID</span>
                            <input name="id" placeholder="id를 입력하세요" type="text" className="form-control"
                                {...register("id", {required: "id를 입력해주세요"})}
                            />
                        </div>
                    </div>
                    <div className={styles["confirm-text"]}>
                        {errors.id && <p>{errors.id.message}</p> }
                    </div><p></p>

                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>이름</span>
                            <input name="userName" placeholder="이름을 입력하세요" type="text" className="form-control"
                                {...register("userName")}
                            />
                        </div>
                    </div>
                    <div className={styles["confirm-text"]}>
                        {errors.userName && <p>{errors.userName.message}</p> }
                    </div><p></p>

                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>닉네임</span>
                            <input name="nickName" placeholder="닉네임을 입력하세요" type="text" className="form-control"
                                {...register("nickName")}
                            />
                            <div className={styles["check-button"]}>
                                <button type="button" className="btn btn-info btn-sm">중복 확인</button>
                            </div>
                        </div>
                    </div>
                    <div className={styles["confirm-text"]}>
                        {errors.nickName && <p>{errors.nickName.message}</p> }
                    </div><p></p>

                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>전화번호</span>
                            <input value={phone} ref={phoneRef} name="phone" onChange={handlePhone} placeholder="010-1234-1234" type="text" className="form-control" />
                        </div>
                    </div><p></p>

                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>이메일</span>
                            <input name="email" placeholder="sample@email.com" type="text" className="form-control"
                                {...register("email")}
                            />
                            <div className={styles["check-button"]}>
                                <button type="button" className="btn btn-info btn-sm">이메일 인증</button>
                            </div>
                        </div>
                    </div>
                    <div className={styles["confirm-text"]}>
                        {errors.email && <p>{errors.email.message}</p> }
                    </div><p></p>

                    {/* 주소 select로 변경 필요*/}
                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>주소</span>
                            <select onChange={handleSi} className="form-select">
                                <option default value="서울시">시도</option>
                                {siList.map((name, index) => {
                                    {console.log(name);}
                                    return (
                                        <option value={name}>{name}</option>
                                    );
                                })}
                            </select>
                            <select className="form-select">
                                <option default value="중구">시군구</option>
                                <option value="중구">중구</option>
                                {/*반복문으로 api에서 받은 리스트 쭉 보여줌*/}
                            </select>
                            <select className="form-select">
                                <option default value="오장동">읍면동</option>
                                <option value="오장동">오장동</option>
                                {/*반복문으로 api에서 받은 리스트 쭉 보여줌*/}
                            </select>
                        </div>
                    </div><p></p>


                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>비밀번호</span>
                            <input type="password" placeholder="비밀번호를 입력하세요" className="form-control"
                                {...register("password")}
                            />
                        </div>
                    </div>
                    <div className={styles["confirm-text"]}>
                        {errors.password && <p>{errors.password.message}</p> }
                    </div><p></p>

                    <div className={styles["input-box"]}>
                        <div className="input-group">
                            <span className={styles["input-header"]}>비밀번호 확인</span>
                            <input type="password" placeholder="비밀번호를 다시 한 번 입력하세요" className="form-control"
                                {...register("passwordConfirm")}
                            />
                        </div>
                    </div>
                    <div className={styles["confirm-text"]}>
                        {errors.passwordConfirm && <p>{errors.passwordConfirm.message}</p> }
                    </div><p></p>

                    <button onClick={handleSubmit(onSubmit)} type="submit" id={styles["submit-button"]}>@_@ 시작하기</button>
                </form>
            </div>
        </div>
    );
}

export default SignUp;