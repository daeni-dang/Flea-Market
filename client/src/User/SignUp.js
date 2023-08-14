import React, { useState, useEffect, useRef } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";

import Header from "../Common/Header";
import styles from "./SignUp.module.css";

import * as yup from "yup";

function SignUp() {
    const [phone, setPhone] = useState("");
    const [si, setSi] = useState(0);
    const [gu, setGu] = useState(0);
    const [dong, setDong] = useState(0);
    const [siList, setSiList] = useState([]);
    const [guList, setGuList] = useState([]);
    const [dongList, setDongList] = useState([]);
    const phoneRef = useRef();
    const navigate = useNavigate();

    // 맨 처음에 siList 불러옴
    useEffect(() => {
        getSi();
    }, [])

    // 비동기 처리를 위한 코드. useState는 비동기라 바로 값이 변경이 안됨.
    useEffect(() => {
        setSi(si);
        getGu();
    }, [si]);

    useEffect(() => {
        setGu(gu);
        getDong();
    }, [gu]);

    // 폼 입력값의 스키마 및 범위 지정
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
        setSi(e.target.value);
    }

    const handleGu = (e) => {
        setGu(e.target.value);
    }

    const handleDong = (e) => {
        setDong(e.target.value);
    }

    const getSi = async () => {
        let url = process.env.REACT_APP_SERVER_URL + "/region/si";
        await axios.get(url)
            .then(async function (response) {
                setSiList(response.data);
            })
    }

    const getGu = () => {
        let url = process.env.REACT_APP_SERVER_URL + "/region/gu/" + si;
        axios.get(url)
            .then(function (response) {
                setGuList(response.data);
            })
    }

    const getDong = () => {
        let url = process.env.REACT_APP_SERVER_URL + "/region/dong/" + gu;
        axios.get(url)
            .then(function (response) {
                setDongList(response.data);
            })
    }

    // 휴대전화번호 입력 시 "-" 자동 생성
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
        let url = process.env.REACT_APP_SERVER_URL + "/user/signup";
        if (window.confirm("회원가입을 완료하시겠습니까?")) {
            axios.post(url, {
                id: data.id,
                userName: data.userName,
                nickName: data.nickName,
                phone: phone,
                email: data.email,
                dongId: dong,
                pwd: data.password,
            })
            navigate("/main");
        }
    }

    return(
        <div>
            <Header />
            <div id={styles["outer"]}>
                <form className={styles["form-wrapper"]}>
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
                                <option>시구</option>
                                {
                                    siList.map((item, index) =>
                                        <option key={index} value={item.id}>{item.name}</option>
                                    )
                                }
                            </select>
                            <select onChange={handleGu} className="form-select">
                                <option>시군구</option>
                                {
                                    guList.map((item, index) =>
                                        <option key={index} value={item.id}>{item.name}</option>
                                    )
                                }
                            </select>
                            <select onChange={handleDong} className="form-select">
                                <option>읍면동</option>
                                {
                                    dongList.map((item, index) =>
                                        <option key={index} value={item.id}>{item.name}</option>
                                    )
                                }
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