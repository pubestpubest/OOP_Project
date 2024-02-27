/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        cadetblue: "#7ab0b4",
        white: "#fff",
        goldenrod: "#ffce50",
        lightcoral: "#ec7272",
        gainsboro: "#d9d9d9",
        black: "#000",
        gray: "#fffdfd",
        red: "#ff0000",
        maroon: "#3f0000",
        khaki: "#ffe976",
      },
      spacing: {},
      fontFamily: {
        inter: "Inter",
        "krona-one": "'Krona One'",
      },
      borderRadius: {
        "22xl": "41px",
        xl: "20px",
        "8xl": "27px",
        "4xs": "9px",
        "10xs-9": "2.9px",
        smi: "13px",
        "45xl": "64px",
        "17xl": "36px",
      },
    },
    fontSize: {
      "61xl": "80px",
      "21xl": "40px",
      "5xl": "24px",
      "109xl": "128px",
      "32xl": "51px",
      "13xl": "32px",
      "7xl": "26px",
      lgi: "19px",
      "34xl-3": "53.3px",
      "24xl": "43px",
      "45xl": "64px",
      "19xl": "38px",
      "8xs-7": "4.7px",
      "41xl": "60px",
      "17xl": "36px",
      "29xl": "48px",
      inherit: "inherit",
    },
    screens: {
      lg: {
        max: "1200px",
      },
      mq1125: {
        raw: "screen and (max-width: 1125px)",
      },
      mq1050: {
        raw: "screen and (max-width: 1050px)",
      },
      mq750: {
        raw: "screen and (max-width: 750px)",
      },
      mq675: {
        raw: "screen and (max-width: 675px)",
      },
      mq450: {
        raw: "screen and (max-width: 450px)",
      },
    },
  },
  corePlugins: {
    preflight: false,
  },
};
