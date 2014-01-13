package li.cil.oc.client

import li.cil.oc.Items
import li.cil.oc.common.inventory.ServerInventory
import li.cil.oc.common.{GuiHandler => CommonGuiHandler, item, tileentity, GuiType}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object GuiHandler extends CommonGuiHandler {
  override def getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
    world.getBlockTileEntity(x, y, z) match {
      case adapter: tileentity.Adapter if id == GuiType.Adapter.id =>
        new gui.Adapter(player.inventory, adapter)
      case computer: tileentity.Case if id == GuiType.Case.id =>
        new gui.Case(player.inventory, computer)
      case drive: tileentity.DiskDrive if id == GuiType.DiskDrive.id =>
        new gui.DiskDrive(player.inventory, drive)
      case proxy: tileentity.RobotProxy if id == GuiType.Robot.id =>
        new gui.Robot(player.inventory, proxy.robot)
      case rack: tileentity.Rack if id == GuiType.Rack.id =>
        new gui.Rack(player.inventory, rack)
      case screen: tileentity.Screen if id == GuiType.Screen.id =>
        new gui.Screen(screen)
      case _ => Items.multi.subItem(player.getCurrentEquippedItem) match {
        case Some(server: item.Server) if id == GuiType.Server.id =>
          new gui.Server(player.inventory, new ServerInventory(player))
        case Some(terminal: item.Terminal) if id == GuiType.Terminal.id =>
          null // TODO
        case _ => null
      }
    }
}
