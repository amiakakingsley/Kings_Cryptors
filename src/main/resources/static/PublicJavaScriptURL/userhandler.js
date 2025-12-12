// Refresh every 3 minutes (180,000 ms)
setInterval(async ()=>{
const activeUser = await getUserProfile();
activeUser ? document.getElementById("login").innerHTML = activeUser.userName : document.getElementById("LoginLink").innerHTML = Login;
}, 1000);



async function getUserProfile() {
    const token = localStorage.getItem("token");

    const res = await fetch("/api/user/me", {
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    const user = await res.json();

    localStorage.setItem("user", JSON.stringify(user));

    return user;
}

document.querySelector(".userRegistrationBtn").addEventListener("click", function () {
    const userName = document.getElementById("userName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if(!userName || !email || !password){
        alert("Enter All field");
        return;
    }

    const applicationUserDto = {
         userName, email, password
    };

    fetch("/api/user/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(applicationUserDto)
    })
    .then(res => res.json())
    .then(data => {
        console.log("Response:", data);
        alert("Registration Successful!");
       showPage("logInSection");
    })
    .catch(err => console.error("Error:", err));
});



document.getElementById("logInFormBtn").addEventListener("click", function () {

    const email = document.getElementById("Loginemail").value;
    const password = document.getElementById("loginpassword").value;

    if (!email || !password) {
        alert("Enter all values");
        return;
    }

    const LoginDto = { email, password };

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(LoginDto)
    })
        .then(async res => {
            const data = await res.json();

            if (!res.ok) {
                alert(data.message || "Login failed");
                return;
            }

            console.log("Response:", data);

            // 🔥 SAVE TOKEN
            localStorage.setItem("token", data.token);

            // 🔥 SAVE USER DETAILS (if your backend sends it)
            if (data.user) {
                localStorage.setItem("user", JSON.stringify(data.user));
            }

            alert("Login Successful!");

            // Redirect to dashboard
            showPage("dashboardSection");
            location.reload(); 
        })
        .catch(err => console.error("Error:", err));
});



function logout() {
    // Remove token and user details from localStorage
    localStorage.removeItem("token");
    localStorage.removeItem("user");

   
    showPage("dashboardSection"); 
    alert("You have been logged out!");

    location.reload();  
}

// Example: attach logout to a button
document.getElementById("logoutBtn").addEventListener("click", logout);
