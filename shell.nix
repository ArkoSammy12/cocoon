let
  nixpkgsVer = "53a2c32bc66f5ae41a28d7a9a49d321172af621e";
  pkgs = import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/${nixpkgsVer}.tar.gz") { config = {}; overlays = []; };
in pkgs.mkShell {
  name = "edtr";

  buildInputs = with pkgs; [
    graalvm-ce
  ];
}
