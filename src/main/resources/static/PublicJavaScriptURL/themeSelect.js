// Load theme when page loads
document.addEventListener("DOMContentLoaded", () => {
  const body = document.body;
  const toggle = document.getElementById("theme-toggle");

  // Apply saved theme (default is dark)
  const savedTheme = localStorage.getItem("theme") || "dark";
  body.className = savedTheme;

  // If this page has a theme toggle button, attach the listener
  if (toggle) {
    toggle.addEventListener("click", () => {
      body.classList.toggle("light");
      body.classList.toggle("dark");

      const currentTheme = body.classList.contains("light") ? "light" : "dark";
      localStorage.setItem("theme", currentTheme);
    });
  }
});

function showPage(pageId, event) {
  // Hide all pages
  document.querySelectorAll(".main").forEach((main) => {
    main.style.display = "none";
  });

  // Show selected page
  document.getElementById(pageId).style.display = "block";

  document.querySelectorAll(".downBtnsection a")
    .forEach(a => a.classList.remove("active"));

    if(event){
      event.target.classList.add("active");

    }
  
}
