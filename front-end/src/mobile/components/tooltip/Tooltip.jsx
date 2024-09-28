import React, { useState } from 'react';
import styles from './Tooltip.module.css';
import TooltipIcon from '../../utils/assets/tooltip.png';

const Tooltip = ({ txt }) => {
    const [showText, setShowText] = useState(false);

    const toggleTooltip = () => {
        setShowText(!showText);
    };

    return (
        <div className={styles.container}>
            <img
                src={TooltipIcon}
                alt="tooltip"
                className={styles.tooltip}
                onClick={toggleTooltip}
            />
            {showText && (
                <p className={styles.tooltipText}>
                    {txt.split("\n").map((line, index) => (
                        <span key={index}>
                            {line}
                            <br />
                        </span>
                    ))}
                </p>
            )}
        </div>
    );
};

export default Tooltip;
