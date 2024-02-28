import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";

const Buttons: NextPage = () => {
  const router = useRouter();

  const onPLAYContainerClick = useCallback(() => {
    router.push("/gameplay");
  }, [router]);

  const onRULESContainerClick = useCallback(() => {
    router.push("/the-rule");
  }, [router]);

  const onConfigContainerClick = useCallback(() => {
    router.push("/config");
  }, [router]);

  return (
    <div
      style={{
        display: "block",
        marginLeft: "auto",
        marginRight: "auto",
        width: "40%",
        marginTop: "-600px",
        marginBottom: "50px ", // ระยะห่างด้านล่าง
      }}
    >
      {/* play*/}
      <div
        style={{
          marginLeft: "auto",
          marginRight: "auto",
          cursor: "pointer",
          width: "300px",
          height: "100px",
          display: "flex",
          justifyContent: "center", //แนวนอน
          alignItems: "center", // แนวตั้ง
          border: "5px solid green", // Optional: add a border for visualization
          borderRadius: 50,
          marginTop: "20px",
          marginBottom: "20px",
          boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.5)", // เงาขอบ
          padding: "5px", // กำหนดการเว้นระยะห่างภายใน div
          transition: "width 0.3s, height 0.3s", // Add transition for smooth effect
        }}
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
        <div
          style={{
            fontSize: "50px", // Font size 50 pixels
            color: "white",
            fontFamily: "monospace",
            textShadow: "0 0 10px rgba(255, 255, 255, 0.8)",
            WebkitTextStroke: "1px rgba(255, 255, 255, 0.2)",
            filter: "drop-shadow(2px 2px 4px rgba(0, 255, 0, 0.8))", // Adding a drop shadow with green color
          }}
        >
          PLAY
        </div>
      </div>
      {/* rules*/}
      <div
        style={{
          marginLeft: "auto",
          marginRight: "auto",
          cursor: "pointer",
          width: "300px",
          height: "100px",
          display: "flex",
          justifyContent: "center", //แนวนอน
          alignItems: "center", // แนวตั้ง
          border: "5px solid orange", // Optional: add a border for visualization
          borderRadius: 50,
          marginTop: "20px",
          marginBottom: "20px",
          boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.5)", // เงาขอบ
          padding: "5px", // กำหนดการเว้นระยะห่างภายใน div
          transition: "width 0.3s, height 0.3s", // Add transition for smooth effect
        }}
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
        <div
          style={{
            fontSize: "50px", // Font size 50 pixels
            color: "white", // Orange color for the text "RULES"
            fontFamily: "monospace",
            textShadow: "0 0 10px rgba(255, 165, 0, 0.8)", // Orange shadow for glow effect
            WebkitTextStroke: "3px rgba(255, 165, 0, 0.2)", // Orange stroke for glow effect
            filter: "drop-shadow(3px 2px 4px rgba(255, 165, 0, 0.8))", // Orange drop shadow for glow effect
          }}
        >
          RULES
        </div>
      </div>
      {/* config*/}
      <div
        style={{
          marginLeft: "auto",
          marginRight: "auto",
          cursor: "pointer",
          width: "300px",
          height: "100px",
          display: "flex",
          justifyContent: "center", //แนวนอน
          alignItems: "center", // แนวตั้ง
          border: "5px solid red", // Optional: add a border for visualization
          borderRadius: 50,
          marginTop: "20px",
          marginBottom: "20px",
          boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.5)", // เงาขอบ
          padding: "5px", // กำหนดการเว้นระยะห่างภายใน div
          transition: "width 0.3s, height 0.3s", // Add transition for smooth effect
        }}
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
        <div
          style={{
            fontSize: "50px", // Font size 50 pixels
            color: "white", // Red color for the text
            fontFamily: "monospace",
            textShadow: "2px 2px 4px rgba(0, 0, 0, 0.5)", // Initial text shadow for glow effect
            WebkitTextStroke: "1px rgba(255, 0, 0, 0.2)", // Red stroke for glow effect
            filter: "drop-shadow(2px 2px 4px red)", // Red drop shadow for glow effect
          }}
        >
          CONFIG
        </div>
      </div>
    </div>
  );
};

export default Buttons;
