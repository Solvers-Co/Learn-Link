import styles from './Tooltip.module.css';
import TooltipIcon from '../../utils/assets/tooltip.png';
import { useState } from 'react';

const Tooltip = ({ txt }) => {
    const [showText, setShowText] = useState(false);

    const toggleTooltip = () => {
        setShowText(!showText);
    };

    return (
        <div className={styles.container}>
            <img src={TooltipIcon} alt="tooltip" 
                className={styles.tooltip}
                onClick={toggleTooltip}
            />
            {showText && (
                <p className={styles.tooltipText}>
                    {txt}
                </p>
            )}
        </div>
    )
}

export default Tooltip;