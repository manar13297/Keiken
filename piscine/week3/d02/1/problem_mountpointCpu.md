- **Check Cgroup Configuration**
```bash 
ls /sys/fs/cgroup
```

Look into your system's cgroup configuration to understand how CPU resources are being managed.


*The presence of /sys/fs/cgroup/cpu.pressure, /sys/fs/cgroup/cpu.stat, etc., suggests that CPU resources might be managed differently on your system.*
>If you can see the /sys/fs/cgroup/cpu, maybe this is not the right solution for you.
- **Edit Grub /etc/default/grub**
Add or update param value of `GRUB_CMDLINE_LINUX` and set it to `systemd.unified_cgroup_hierarchy=0`

(GRUB_CMDLINE_LINUX="systemd.unified_cgroup_hierarchy=0")
- **update grub**
```bash
  sudo update-grub
 ```
- **reboot system**