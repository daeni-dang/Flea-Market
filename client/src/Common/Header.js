import { Link } from 'react-router-dom';

import styles from './Header.module.css';

function Header() {
    return(
        <div>
            <div id={styles["header-bar"]}>
                <div id={styles["title"]}>
                    @_@
                </div>
                <div id={styles["login-button-wrapper"]}>
                    <Link to="/login">
                        <button className="btn btn-dark" type="submit">
                            로그인
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default Header;