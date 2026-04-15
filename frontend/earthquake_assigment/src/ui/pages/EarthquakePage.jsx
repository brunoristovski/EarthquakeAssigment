import { useState } from "react";
import earthquakeRepository from "../../repository/earthquakeRepo.js";

const EarthquakesPage = () => {
    const [earthquakes, setEarthquakes] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [date, setDate] = useState("");

    const handleFetch = async () => {
        try {
            const res = await earthquakeRepository.fetchEarthquakes();
            setEarthquakes(res.data);
            setLoaded(true);
        } catch (err) {
            console.error(err);
        }
    };

    const handleFilterMagnitude = async () => {
        try {
            const res = await earthquakeRepository.filterByMagnitude();
            setEarthquakes(res.data);
            setLoaded(true);
        } catch (err) {
            console.error(err);
        }
    };

    const handleFilterDate = async () => {
        try {
            if (!date) return;

            const timestamp = new Date(date).getTime();
            const res = await earthquakeRepository.filterByDate(timestamp);

            setEarthquakes(res.data);
            setLoaded(true);
        } catch (err) {
            console.error(err);
        }
    };

    const handleDelete = async (id) => {
        try {
            await earthquakeRepository.deleteEarthquake(id);
            setEarthquakes(prev => prev.filter(e => e.id !== id));
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div style={styles.page}>
            <div style={styles.container}>

                <h1 style={styles.title}>🌍 Earthquake Dashboard</h1>


                <div style={styles.card}>
                    <button style={styles.buttonPrimary} onClick={handleFetch}>
                        Fetch Earthquakes
                    </button>

                    <button style={styles.buttonSecondary} onClick={handleFilterMagnitude}>
                        Magnitude {'>'} 2
                    </button>
                </div>


                <div style={styles.cardRow}>
                    <input
                        type="datetime-local"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        style={styles.input}
                    />

                    <button style={styles.buttonPrimary} onClick={handleFilterDate}>
                        Filter By Date
                    </button>
                </div>

                {loaded && (
                    <div style={styles.tableWrapper}>
                        {earthquakes.length === 0 ? (
                            <p style={styles.empty}>No earthquakes found.</p>
                        ) : (
                            <table style={styles.table}>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Magnitude</th>
                                    <th>Place</th>
                                    <th>Title</th>
                                    <th>Date</th>
                                    <th>Lat</th>
                                    <th>Lon</th>
                                    <th>Action</th>
                                </tr>
                                </thead>

                                <tbody>
                                {earthquakes.map(eq => (
                                    <tr key={eq.id} style={styles.row}>
                                        <td>{eq.id}</td>
                                        <td>
                                                <span style={{
                                                    ...styles.badge,
                                                    backgroundColor: eq.magnitude > 5 ? "#ff4d4d" : "#ffa726"
                                                }}>
                                                    {eq.magnitude}
                                                </span>
                                        </td>
                                        <td>{eq.place}</td>
                                        <td>{eq.title}</td>
                                        <td>{eq.date}</td>
                                        <td>{eq.latitude}</td>
                                        <td>{eq.longitude}</td>
                                        <td>
                                            <button
                                                style={styles.deleteBtn}
                                                onClick={() => handleDelete(eq.id)}
                                            >
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
};

const styles = {
    page: {
        minHeight: "100vh",
        background: "#0f172a",
        display: "flex",
        justifyContent: "center",
        padding: "30px"
    },
    container: {
        width: "100%",
        maxWidth: "1100px",
        color: "#fff"
    },
    title: {
        marginBottom: "20px",
        fontSize: "28px"
    },
    card: {
        background: "#1e293b",
        padding: "15px",
        borderRadius: "12px",
        marginBottom: "15px",
        display: "flex",
        gap: "10px",
        alignItems: "center"
    },
    cardRow: {
        background: "#1e293b",
        padding: "15px",
        borderRadius: "12px",
        marginBottom: "20px",
        display: "flex",
        gap: "10px",
        alignItems: "center"
    },
    input: {
        padding: "10px",
        borderRadius: "8px",
        border: "1px solid #334155",
        background: "#0f172a",
        color: "#fff"
    },
    buttonPrimary: {
        padding: "10px 14px",
        borderRadius: "8px",
        border: "none",
        background: "#3b82f6",
        color: "white",
        cursor: "pointer"
    },
    buttonSecondary: {
        padding: "10px 14px",
        borderRadius: "8px",
        border: "none",
        background: "#10b981",
        color: "white",
        cursor: "pointer"
    },
    tableWrapper: {
        background: "#1e293b",
        padding: "15px",
        borderRadius: "12px",
        overflowX: "auto"
    },
    table: {
        width: "100%",
        borderCollapse: "collapse"
    },
    row: {
        borderBottom: "1px solid #334155"
    },
    badge: {
        padding: "4px 8px",
        borderRadius: "6px",
        color: "white",
        fontSize: "12px"
    },
    deleteBtn: {
        padding: "6px 10px",
        background: "#ef4444",
        color: "white",
        border: "none",
        borderRadius: "6px",
        cursor: "pointer"
    },
    empty: {
        textAlign: "center",
        padding: "20px",
        color: "#94a3b8"
    }
};

export default EarthquakesPage;