import '../../components/dashboard/graficoLinhas/GraficoLinhas.module.css';
import Metricas from '../../components/dashboard/metricas/Metricas';
import styles from './Dashboard.module.css';
import MenuLateral from '../../components/dashboard/menuLateral/MenuLateral';
import Ranking from '../../components/dashboard/ranking/Ranking';


function Dashboard() {
  return (
    <div className={styles["telaDashboard"]}>
      <MenuLateral />
      <Metricas />
      <Ranking />
    </div>
  );
}

export default Dashboard;