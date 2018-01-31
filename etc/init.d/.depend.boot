TARGETS = console-setup resolvconf mountkernfs.sh hostname.sh udev keyboard-setup mountdevsubfs.sh procps urandom hwclock.sh networking checkroot.sh bootmisc.sh checkfs.sh checkroot-bootclean.sh kmod mountnfs.sh mountall.sh mountnfs-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
networking: resolvconf mountkernfs.sh urandom procps
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
bootmisc.sh: udev checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
mountnfs.sh: networking
mountall.sh: checkfs.sh checkroot-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh
