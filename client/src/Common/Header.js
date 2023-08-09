import styles from './Header.module.css';

function Header() {
    return(
        <div>
            <div id={styles["header-bar"]}>
                <div id={styles["title"]}>
                    @_@
                </div>
                <div id={styles["login-button-wrapper"]}>
                    <button className="btn btn-dark" type="submit">
                        로그인
                    </button>
                </div>
            </div>
        </div>
    )
}

export default Header;