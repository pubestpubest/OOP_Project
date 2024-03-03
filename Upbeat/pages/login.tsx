import React, { useState } from "react";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  // Handle login logic (e.g., send login request to server)
  const handleLogin = (event) => {
    event.preventDefault();
    // Check credentials and store username in local storage on success
    localStorage.setItem("username", username);

    // Redirect to the gameplay page or display a success message
  };

  return (
    <form onSubmit={handleLogin}>
      <label htmlFor="username">Username:</label>
      <input
        type="text"
        id="username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <button type="submit">Login</button>
    </form>
  );
}

export default Login;
