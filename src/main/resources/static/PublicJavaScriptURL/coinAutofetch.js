async function loadCoins() {
    try {
        const response = await fetch("/coins");
        const coins = await response.json();

        const tableBody = document.querySelector("#coinTable tbody");
        tableBody.innerHTML = "";

        coins.forEach(coin => {
            const row = `
                <tr>
                    <td><img src="${coin.image}" border-radius="50%" width="20"> ${coin.name}</td>
                    <td>$${coin.current_price.toLocaleString()}</td>
                    <td style="color: ${coin.price_change_percentage_24h >= 0 ? 'lightgreen' : 'red'}">
                        ${coin.price_change_percentage_24h.toFixed(2)}%
                    </td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });

    } catch (error) {
        console.log("Error fetching coins:", error);
    }
}

// Load immediately when page opens
loadCoins();

// Refresh every 3 minutes (180,000 ms)
setInterval(loadCoins, 180000);
