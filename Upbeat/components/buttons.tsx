import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";

const Buttons: NextPage = () => {
  const router = useRouter();

  const onPLAYContainerClick = useCallback(() => {
    // router.push("/gameplay");
    router.push("/login");
  }, [router]);

  const onRULESContainerClick = useCallback(() => {
    router.push("/the-rule");
  }, [router]);

  const onConfigContainerClick = useCallback(() => {
    router.push("/config");
  }, [router]);
  const buttonsstyle = {
    marginLeft: "auto",
    marginRight: "auto",
    cursor: "pointer",
    width: "300px",
    height: "100px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    border: "5px solid white",
    borderRadius: 50,
    marginTop: "20px",
    marginBottom: "20px",
    boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.5)",
    padding: "5px",
    transition: "width 0.3s, height 0.3s",
  };
  const frontstyle = {
    fontSize: "50px",
    color: "white",
    fontFamily: "monospace",
    textShadow: "0 0 10px rgba(0, 0, 0, 0)",
    WebkitTextStroke: "1px rgba(255, 255, 255, 0.2)",
    filter: "drop-shadow(2px 2px 4px rgba(0, 0, 0, 0))",
  };

  return (
    <div
      style={{
        display: "block",
        marginLeft: "auto",
        marginRight: "auto",
        width: "40%",
        marginBottom: "50px", // Adjusted marginBottom for spacing
      }}
    >
      {/* play*/}
      <div
        style={buttonsstyle}
        onClick={onPLAYContainerClick}
        onMouseEnter={(e) => {
          const target = e.target as HTMLElement;
          target.style.width = "275px";
          target.style.height = "90px";
        }}
        onMouseLeave={(e) => {
          const target = e.target as HTMLElement;
          target.style.width = "300px";
          target.style.height = "100px";
        }}
      >
        <div style={frontstyle}>PLAY</div>
      </div>
      {/* rules*/}
      <div
        style={buttonsstyle}
        onClick={onRULESContainerClick}
        onMouseEnter={(e) => {
          const target = e.target as HTMLElement;
          target.style.width = "275px";
          target.style.height = "90px";
        }}
        onMouseLeave={(e) => {
          const target = e.target as HTMLElement;
          target.style.width = "300px";
          target.style.height = "100px";
        }}
      >
        <div style={frontstyle}>RULES</div>
      </div>
      {/* config*/}
      <div
        style={buttonsstyle}
        onClick={onConfigContainerClick}
        onMouseEnter={(e) => {
          const target = e.target as HTMLElement;
          target.style.width = "275px";
          target.style.height = "90px";
        }}
        onMouseLeave={(e) => {
          const target = e.target as HTMLElement;
          target.style.width = "300px";
          target.style.height = "100px";
        }}
      >
        <div style={frontstyle}>CONFIG</div>
      </div>
    </div>
  );
};

export default Buttons;
