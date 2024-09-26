package chipyard

import chisel3._

import freechips.rocketchip.config.{Config}

// ---------------------
// Xiangshan Configs
// ---------------------


class XiangshanConfig extends Config(
  new xiangshan.WithNXiangshanCores(1) ++
  new chipyard.config.AbstractConfig)